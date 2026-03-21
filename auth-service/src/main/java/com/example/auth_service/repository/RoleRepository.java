package com.example.auth_service.repository;

import java.lang.StackWalker.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.entity.Role;
import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRole(String role);
}
