package com.example.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.EventDeletedUserKafka;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).get();

        userRepository.delete(user);

        applicationEventPublisher.publishEvent(new EventDeletedUserKafka(userId));

    }

    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }

}
