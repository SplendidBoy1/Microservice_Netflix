package com.example.movie_service.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.movie_service.entity.Movie;

import java.util.Optional;



public interface MovieRepository extends JpaRepository<Movie, Long>{
    Optional<Movie> findBySlug(String slug);
    
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.keywords k where LOWER(k.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(m.originalName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Optional<Page<Movie>> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
