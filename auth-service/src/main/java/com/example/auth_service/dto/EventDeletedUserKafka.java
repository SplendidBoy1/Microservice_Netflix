package com.example.auth_service.dto;

public class EventDeletedUserKafka {
    
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EventDeletedUserKafka(){

    }

    public EventDeletedUserKafka(Long userID){
        this.userId = userID;
    }

}
