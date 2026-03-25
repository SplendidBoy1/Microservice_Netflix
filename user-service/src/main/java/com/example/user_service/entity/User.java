package com.example.user_service.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class User {
    
    @Id
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username")
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "full_name")
    private String fullName;

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "birthdate")
    private LocalDate date;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    public LocalDateTime getUpdatedTime() {
        return this.updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Column(name = "gender")
    private String gender;

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @PrePersist
    public void prePersist(){
        this.createdTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedTime = LocalDateTime.now();
    }

    public User(){

    }

    public User(Long id, String username){
        this.id = id;
        this.username = username;
    }

}
