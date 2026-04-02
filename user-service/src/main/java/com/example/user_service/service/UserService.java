package com.example.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.CustomUserDetails;
import com.example.user_service.dto.EventDeletedUserKafka;
import com.example.user_service.dto.MovieIdRequestDTO;
import com.example.user_service.dto.ResponseMovieDTO;
import com.example.user_service.dto.UserDTO;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.dto.UserResponse;
import com.example.user_service.entity.Favorite;
import com.example.user_service.entity.User;
import com.example.user_service.repository.FavoriteRepository;
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

    public UserResponse findUser(CustomUserDetails userDetails){
        System.out.println("UserId = " + userDetails.getId());
        System.out.println("Username" + userDetails.getUsername());
        
        User user = userRepository.findById(userDetails.getId()).get();
        return new UserResponse(user.getFullName(), user.getDate(), user.getGender());
    }


    @Transactional
    public String updateUser(CustomUserDetails userDetails, UserRequest request){
        User user = userRepository.findById(userDetails.getId()).get();

        user.setFullName(request.getFullName());    
        user.setDate(request.getDate());
        user.setGender(request.getGender());

        return "Update user information successful";
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> new UserDTO(user.getId(), user.getFullName(), user.getDate(), user.getGender()));
    }

    @Transactional
    public String updateUserDTO(UserDTO userDTO){
        User user = userRepository.findById(userDTO.getUserId()).get();
        user.setFullName(userDTO.getFullName());
        user.setDate(userDTO.getDate());
        user.setGender(userDTO.getGender());
        return "Update user successful";
    }

    

}
