package com.example.user_service.dto;

import java.time.LocalDate;

public class UserResponse {
    private String fullName;

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private LocalDate date;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    

    private String gender;

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserResponse(String fullName, LocalDate date, String gender){
        this.fullName = fullName;
        this.date = date;
        this.gender = gender;
    }

    
}
