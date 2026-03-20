package com.example.movie_service.dto;

import java.util.List;

import com.example.movie_service.entity.Image;
import com.example.movie_service.entity.Movie;

public class MovieMainPageDTO {
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String original_name;

    public String getOriginal_name() {
        return this.original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    private String slug;

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    private Integer release_year;

    public Integer getRelease_year() {
        return this.release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    private String imdbRating;

    public String getImdbRating() {
        return this.imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    private List<Image> images;

    public List<Image> getImages() {
        return this.images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public MovieMainPageDTO(Movie movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.original_name = movie.getOriginalName();
        this.release_year = movie.getReleaseYear();
        this.imdbRating = movie.getImdbRating();
        List<Image> posters = movie.getImages().stream().filter(img -> img.getType().equals("poster")).toList();
        this.images = posters;
        this.slug = movie.getSlug();
    }

    public MovieMainPageDTO(){

    }

}
