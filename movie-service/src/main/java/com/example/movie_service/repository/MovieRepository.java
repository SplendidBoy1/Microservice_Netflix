package com.example.movie_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie_service.entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long>{
}
