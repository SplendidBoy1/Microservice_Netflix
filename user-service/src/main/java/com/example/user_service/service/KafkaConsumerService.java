package com.example.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.EventCreatedUserKafka;
import com.example.user_service.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
    
    @Autowired
    private UserService userService;

    @KafkaListener(topics = "create-user")
    public void consume(String event){
        System.out.println(event);
        try{
            ObjectMapper mapper = new ObjectMapper();
            EventCreatedUserKafka json_event = mapper.readValue(event, EventCreatedUserKafka.class);
            User new_user = new User();

            new_user.setId(json_event.getUserId());
            new_user.setUsername(json_event.getUsername());

            userService.saveUser(new_user);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // User new_user = new User();

        // new_user.setId(event.getUserId());
        // new_user.setUsername(event.getUsername());

        // userRepository.save(new_user);

    }

}
