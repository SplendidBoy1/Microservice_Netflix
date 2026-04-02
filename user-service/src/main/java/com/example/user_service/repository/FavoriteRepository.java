package com.example.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_service.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    List<Favorite> findByUserId(Long userId);
}