package com.example.movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movie_service.dto.MovieMainPageDTO;
import com.example.movie_service.entity.Movie;
import com.example.movie_service.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping
    public Page<MovieMainPageDTO> getMovies(@PageableDefault(page = 0, size = 10)Pageable pageable) {
        return movieService.getAllMovies(pageable);
    }
    
    @GetMapping("/{slug_movie}")
    public Movie getMoviesDetails(@PathVariable("slug_movie") String slug){
        return movieService.getMovieDetail(slug);
    }

    @GetMapping("/search")
    public Page<MovieMainPageDTO> searchMovie(@RequestParam String keyword, @PageableDefault(page = 0, size = 10) Pageable pageable){
        return movieService.search(keyword, pageable);
    }
}   
