package com.example.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "favorites", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "movie_id"}))
public class Favorite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    public Long getMovieId() {
        return this.movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Favorite(){

    }

    public Favorite(Long userId, Long movieId){
        this.userId = userId;
        this.movieId = movieId;
    }

}
