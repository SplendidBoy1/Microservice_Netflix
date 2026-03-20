package com.example.movie_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="slug")
    private String slug;

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name="original_name")
    private String originalName;

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Column(name="release_year")
    private Integer releaseYear;

    public Integer getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(name="time")
    private String time;

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ManyToMany
    @JoinTable(name = "movie_categories", 
        joinColumns = @JoinColumn(name = "movie_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @ManyToMany
    @JoinTable(name = "movie_countries", 
        joinColumns = @JoinColumn(name = "movie_id"), 
        inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries;

    public List<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Movie(){

    }

    @Column(name="type")
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "content", length = 1000)
    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name="trailer_url")
    private String trailerUrl;

    public String getTrailerUrl() {
        return this.trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Column(name="tmdb_rating")
    private String tmdbRating;

    public String getTmdbRating() {
        return this.tmdbRating;
    }

    public void setTmdbRating(String tmdbRating) {
        this.tmdbRating = tmdbRating;
    }

    @Column(name="imdb_rating")
    private String imdbRating;

    public String getImdbRating() {
        return this.imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @ManyToMany
    @JoinTable(name = "movie_people", 
        joinColumns = @JoinColumn(name = "movie_id"), 
        inverseJoinColumns = @JoinColumn(name = "people_id"))
    private List<People> peoples;

    public List<People> getPeoples() {
        return this.peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EpisodeServer> episodeServers;

    public List<EpisodeServer> getEpisodeServers() {
        return this.episodeServers;
    }

    public void setEpisodeServers(List<EpisodeServer> episodeServers) {
        this.episodeServers = episodeServers;
    }

    public Movie(String name, String slug, String originalName, Integer releaseYear, String time) {
        this.name = name;
        this.slug = slug;
        this.originalName = originalName;
        this.releaseYear = releaseYear;
        this.time = time;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public List<Image> getImages() {
        return this.images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @ManyToMany
    @JoinTable(
    name = "movie_keyword",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<Keyword> keywords;

    public List<Keyword> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }


}
