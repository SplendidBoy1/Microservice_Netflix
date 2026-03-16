package com.example.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
