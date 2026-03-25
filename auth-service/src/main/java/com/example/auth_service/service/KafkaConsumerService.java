package com.example.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.auth_service.dto.EventCreatedUserKafka;
import com.example.auth_service.dto.EventDeletedUserKafka;
import com.example.auth_service.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
     
    @Autowired
    private AuthService authService;

    @KafkaListener(topics = "delete-user")
    public void deleteconsume(String event){
        System.out.println(event);
        try{
            ObjectMapper mapper = new ObjectMapper();
            EventDeletedUserKafka json_event = mapper.readValue(event, EventDeletedUserKafka.class);
            authService.deleteAuthUser(json_event.getUserId());
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
