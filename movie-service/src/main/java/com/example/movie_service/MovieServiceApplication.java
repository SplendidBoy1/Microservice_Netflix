package com.example.movie_service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.movie_service.entity.Movie;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.service.CategoryService;
import com.example.movie_service.service.CountryService;
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
					movies.add(movie);
					//movieRepository.save(movie);
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
