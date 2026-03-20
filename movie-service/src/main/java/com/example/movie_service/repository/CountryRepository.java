package com.example.movie_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie_service.entity.Country;


public interface CountryRepository extends JpaRepository<Country, Long>{
    Optional<Country> findByName(String name);
}


