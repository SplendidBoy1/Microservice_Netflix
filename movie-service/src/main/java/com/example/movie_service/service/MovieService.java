package com.example.movie_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movie_service.dto.MovieMainPageDTO;
import com.example.movie_service.entity.Movie;
import com.example.movie_service.repository.MovieRepository;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<MovieMainPageDTO> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movie -> new MovieMainPageDTO(movie));
    }
    
    public Movie getMovieDetail(String movie_slug){
        return movieRepository.findBySlug(movie_slug).get();
    }

    public Page<MovieMainPageDTO> search(String keyword, Pageable pageable){
        return movieRepository.searchByKeyword(keyword, pageable).get().map(movie -> new MovieMainPageDTO(movie));
    }

}
