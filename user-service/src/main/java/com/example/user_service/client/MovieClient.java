package com.example.user_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.user_service.dto.MovieIdRequestDTO;
import com.example.user_service.dto.ResponseMovieDTO;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("/api/movies/id")
    public List<ResponseMovieDTO> getMovieById(@RequestBody MovieIdRequestDTO list_movieId);
}