package com.example.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.CustomUserDetails;
import com.example.user_service.dto.FavoriteRequestDTO;
import com.example.user_service.dto.ResponseMovieDTO;
import com.example.user_service.service.FavoriteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users/me/fav")
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping()  
    public List<ResponseMovieDTO> getFavariteMovie(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        System.out.println(authentication.getPrincipal().toString());
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return favoriteService.findFavoriteMovies(user);
    }

    @PostMapping()
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        System.out.println(authentication.getPrincipal().toString());
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(favoriteService.addFavoriteMovie(user, request));
    }

}
