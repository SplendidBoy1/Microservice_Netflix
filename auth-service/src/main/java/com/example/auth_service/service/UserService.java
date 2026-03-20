package com.example.auth_service.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.auth_service.entity.User;
import com.example.auth_service.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(String username){
        return userRepository.findByUsername(username).get();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
    .map(role -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_" + role.getRole()))
    .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

}
