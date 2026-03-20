package com.example.movie_service.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="peoples")
public class People {
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

    @Column(name = "profile_path")
    private String path;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "original_name")
    private String originalName;

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Column(name = "character_name")
    private String characterName;

    public String getcharacterName() {
        return this.characterName;
    }

    public void setcharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Column(name="gender")
    private String gender;

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ManyToMany(mappedBy = "peoples")
    @JsonIgnore
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


    public People (){

    }

}
