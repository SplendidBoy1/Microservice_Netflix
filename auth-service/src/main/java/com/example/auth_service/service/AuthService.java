package com.example.auth_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.auth_service.dto.EventCreatedUserKafka;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.entity.Role;
import com.example.auth_service.entity.User;
import com.example.auth_service.repository.RoleRepository;
import com.example.auth_service.repository.UserRepository;

import org.springframework.context.ApplicationEventPublisher;
import jakarta.transaction.Transactional;


@Service
public class AuthService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private KafkaProducerService kafkaService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public AuthService(UserRepository userRepository){
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


    @Transactional
    public String register(RegisterRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("User already exist");
        }

        User new_user = new User();
        new_user.setUsername(request.getUsername());
        new_user.setPassword("{noop}" + request.getPassword());
        new_user.setEnabled(true);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRole("USER").get());
        new_user.setRoles(roles);
        userRepository.save(new_user);

        applicationEventPublisher.publishEvent(new EventCreatedUserKafka(new_user.getId(), new_user.getUsername()));
        
        // kafkaService.sendMessage("create-user", new EventCreatedUserKafka(new_user.getId(), new_user.getUsername()));
        return "User registered successfully";
    }

    @Transactional
    public void deleteAuthUser(Long id){

        User user = userRepository.findById(id).get();

        userRepository.delete(user);

    }

}
