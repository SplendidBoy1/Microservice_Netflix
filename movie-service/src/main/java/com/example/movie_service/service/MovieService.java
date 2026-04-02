package com.example.movie_service.service;


import com.example.movie_service.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movie_service.dto.MovieIdRequestDTO;
import com.example.movie_service.dto.MovieMainPageDTO;
import com.example.movie_service.dto.ResponseMovieDTO;
import com.example.movie_service.entity.Movie;
import com.example.movie_service.repository.MovieRepository;

import jakarta.transaction.Transactional;


@Service
public class MovieService {

    private final CategoryRepository categoryRepository;
    @Autowired
    private MovieRepository movieRepository;

    MovieService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<MovieMainPageDTO> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movie -> new MovieMainPageDTO(movie));
    }
    
    public Movie getMovieDetail(String movie_slug){
        return movieRepository.findBySlug(movie_slug).get();
    }

    public Page<MovieMainPageDTO> search(String keyword, Pageable pageable){
        return movieRepository.searchByKeyword(keyword, pageable).get().map(movie -> new MovieMainPageDTO(movie));
    }

    @Transactional
    public String deleteMovie(Long id){

        Movie movie = movieRepository.findById(id).get();

        movieRepository.delete(movie);

        return "Delete movie successfully";
    }

    public List<ResponseMovieDTO> getMovieById(MovieIdRequestDTO list_movieId){

        List<ResponseMovieDTO> movies = movieRepository.findAllById(list_movieId.getIds()).stream().map(movie -> new ResponseMovieDTO(movie)).toList();
        return movies;
    }

}
