package com.example.movie_service.repository;

import com.example.movie_service.entity.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findByName(String name);
}
