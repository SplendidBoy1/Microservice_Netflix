package com.example.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.CustomUserDetails;
import com.example.user_service.dto.ResponseMovieDTO;
import com.example.user_service.dto.UserDTO;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.dto.UserResponse;
import com.example.user_service.entity.User;
import com.example.user_service.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/me")
    public UserResponse getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        System.out.println(authentication.getPrincipal().toString());
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return userService.findUser(user);
    }
    
    @PutMapping("/me")
    public ResponseEntity<?> updateMyProfile(@RequestBody UserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        System.out.println(authentication.getPrincipal().toString());
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.updateUser(user, request));
    }

    @GetMapping
    public Page<UserDTO> getMovies(@PageableDefault(page = 0, size = 10)Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody UserDTO request) {
        return ResponseEntity.ok(userService.updateUserDTO(request));
    }

    
    

}
