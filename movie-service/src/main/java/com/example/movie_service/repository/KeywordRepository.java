package com.example.movie_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie_service.entity.Keyword;


public interface KeywordRepository extends JpaRepository<Keyword, Long>{
    Optional<Keyword> findByName(String name);;
    
}