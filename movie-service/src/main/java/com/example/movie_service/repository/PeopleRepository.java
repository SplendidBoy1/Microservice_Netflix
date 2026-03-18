package com.example.movie_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie_service.entity.People;
import java.util.Optional;


public interface PeopleRepository extends JpaRepository<People, Long>{
    Optional<People> findById(Long id);
    
}
