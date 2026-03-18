package com.example.movie_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.movie_service.entity.Episode;
import com.example.movie_service.entity.EpisodeServer;
import com.example.movie_service.entity.Image;
import com.example.movie_service.entity.Movie;
import com.example.movie_service.entity.People;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.repository.PeopleRepository;
import com.example.movie_service.service.CategoryService;
import com.example.movie_service.service.CountryService;
import com.example.movie_service.service.KeywordService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
public class MovieServiceApplication {

    @Autowired
	private MovieRepository movieRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private KeywordService keywordService;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner run(RestTemplate restTemplate){
		return arg -> {

			movieRepository.deleteAllInBatch();
			categoryService.deleteAll();
			countryService.deleteAll();
			peopleRepository.deleteAllInBatch();
			for (int num_page = 1; num_page <= 1; num_page++){

				String base_url = "https://ophim1.com/v1/api/danh-sach/phim-moi?page=";



				String url = base_url + num_page;

				ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

				ObjectMapper mapper = new ObjectMapper();

				JsonNode jsonNode = mapper.readTree(response.getBody());

				//System.out.println(jsonNode.toPrettyString());

				JsonNode items = jsonNode.get("data").get("items");

				System.out.println("ASDFASFDSAFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

				//System.out.println(items.toPrettyString());

				//System.out.println(items.isArray());

				List<Movie> movies = new ArrayList<>();
				items.forEach(item -> {
				try{
					String slug = item.get("slug").toString().replace("\"", "");
					String url_details = "https://ophim1.com/v1/api/phim/" + slug;
					//System.out.println(url_details);
					ResponseEntity<String> response_movie = restTemplate.getForEntity(url_details, String.class);
					JsonNode movie_Detail = mapper.readTree(response_movie.getBody()).get("data").get("item");
					//System.out.println(movie_Detail.toString());
					Movie movie = new Movie();
					movie.setName(movie_Detail.get("name").toString().replace("\"", ""));
					movie.setContent(movie_Detail.get("content").toString().replace("\"", ""));
					movie.setOriginalName(movie_Detail.get("origin_name").toString().replace("\"", ""));
					movie.setTrailerUrl(movie_Detail.get("trailer_url").toString().replace("\"", ""));
					movie.setSlug(movie_Detail.get("slug").toString().replace("\"", ""));
					movie.setReleaseYear(Integer.parseInt(movie_Detail.get("year").toString().replace("\"", "")));
					movie.setTime(movie_Detail.get("time").toString().replace("\"", ""));
					movie.setCategories(categoryService.saveCategories(movie_Detail.get("category")));
					movie.setCountries(countryService.saveCategories(movie_Detail.get("country")));
					movie.setImdbRating(movie_Detail.get("imdb").get("vote_average").toString().replace("\"", ""));
					movie.setTmdbRating(movie_Detail.get("tmdb").get("vote_average").toString().replace("\"", ""));
					movie.setType(movie_Detail.get("type").toString().replace("\"", ""));
					ResponseEntity<String> response_people = restTemplate.getForEntity(url_details + "/peoples", String.class);
					JsonNode peoplesNode = mapper.readTree(response_people.getBody()).get("data").get("peoples");
					List<People> peoples = new ArrayList<>();
					for(JsonNode peopleNode : peoplesNode){
						People tempPeople = new People();
						tempPeople.setOriginalName(peopleNode.get("original_name").toString().replace("\"", ""));
						tempPeople.setcharacterName(peopleNode.get("character").toString().replace("\"", ""));
						tempPeople.setGender(peopleNode.get("gender_name").toString().replace("\"", ""));
						tempPeople.setPath(peopleNode.get("profile_path").toString().replace("\"", ""));
						peopleRepository.save(tempPeople);
						peoples.add(tempPeople);
					}
					movie.setPeoples(peoples);
					JsonNode json_episodeservers = movie_Detail.get("episodes");
					List<EpisodeServer> list_EpisodeServers = new ArrayList<>();
					for (JsonNode json_episodeserver : json_episodeservers){
						EpisodeServer temp_epEpisodeServer = new EpisodeServer();
						temp_epEpisodeServer.setMovie(movie);
						temp_epEpisodeServer.setName(json_episodeserver.get("server_name").toString());
						JsonNode json_episodes = json_episodeserver.get("server_data");
						//System.out.println(json_episodes.toPrettyString());
						List<Episode> list_episodes = new ArrayList<>();
						for (JsonNode json_episode: json_episodes){
							Episode episode = new Episode();
							episode.setEpisodeServer(temp_epEpisodeServer);
							episode.setName(json_episode.get("name").toString().replace("\"", ""));
							episode.setSlug(json_episode.get("slug").toString().replace("\"", ""));
							episode.setLinkEmbed(json_episode.get("link_embed").toString().replace("\"", ""));
							episode.setLinkM3u8(json_episode.get("link_m3u8").toString().replace("\"", ""));
							list_episodes.add(episode);
						}
						temp_epEpisodeServer.setEpisodes(list_episodes);
						list_EpisodeServers.add(temp_epEpisodeServer);
					}
					movie.setEpisodeServers(list_EpisodeServers);
					ResponseEntity<String> response_images = restTemplate.getForEntity(url_details + "/images", String.class);
					JsonNode imageNodes = mapper.readTree(response_images.getBody()).get("data").get("images");
					String backdrop = mapper.readTree(response_images.getBody()).get("data").get("image_sizes").get("backdrop").get("original").toString().replace("\"", "");
					List<Image> images = new ArrayList<>();
					for (JsonNode imageNode: imageNodes){
						//System.out.println(imageNode.toString());
						Image img = new Image();
						img.setWidth(imageNode.get("width").asInt());
						img.setHeight(imageNode.get("height").asInt());
						img.setAspectRatio(imageNode.get("aspect_ratio").asDouble());
						img.setType(imageNode.get("type").toString().replace("\"", ""));
						img.setBackdrop(backdrop);
						img.setFilePath(imageNode.get("file_path").toString().replace("\"", ""));
						img.setMovie(movie);
						images.add(img);
					}
					//System.out.println(json_episodeserver.toPrettyString());
					movie.setImages(images);
					ResponseEntity<String> response_keyword = restTemplate.getForEntity(url_details + "/keywords", String.class);
					JsonNode keywordNodes = mapper.readTree(response_keyword.getBody()).get("data").get("keywords");

					movie.setKeywords(keywordService.saveKeywords(keywordNodes));

					movieRepository.save(movie);
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			);
			movieRepository.saveAll(movies);
			}
		};
	}


}
