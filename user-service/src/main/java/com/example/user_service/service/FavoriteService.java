package com.example.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_service.client.MovieClient;
import com.example.user_service.dto.CustomUserDetails;
import com.example.user_service.dto.FavoriteRequestDTO;
import com.example.user_service.dto.MovieIdRequestDTO;
import com.example.user_service.dto.ResponseMovieDTO;
import com.example.user_service.entity.Favorite;
import com.example.user_service.repository.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class FavoriteService {
    
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MovieClient movieClient;


    @Transactional
    public List<ResponseMovieDTO> findFavoriteMovies(CustomUserDetails userDetails){
        List<Favorite> list_fav = favoriteRepository.findByUserId(userDetails.getId());

        System.out.println(list_fav);
        List<Long> list_movieId = list_fav.stream().map(fav -> fav.getMovieId()).toList();

        System.out.println(list_movieId);

        MovieIdRequestDTO movieIdRequestDTO = new MovieIdRequestDTO(list_movieId);

        return movieClient.getMovieById(movieIdRequestDTO);

    }

    @Transactional
    public String addFavoriteMovie(CustomUserDetails userDetails, FavoriteRequestDTO favoriteRequestDTO){
        System.out.println(favoriteRequestDTO.getMovieId());
        Favorite favorite = new Favorite(userDetails.getId(), favoriteRequestDTO.getMovieId());
        favoriteRepository.save(favorite);
        return "Add to favorite list succesfully";
    }
}
