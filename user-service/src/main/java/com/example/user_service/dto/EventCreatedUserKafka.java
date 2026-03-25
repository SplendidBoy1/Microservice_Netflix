package com.example.user_service.dto;

public class EventCreatedUserKafka {
    
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EventCreatedUserKafka(){

    }

    public EventCreatedUserKafka(Long userID, String username){
        this.userId = userID;
        this.username = username;
    }
}
