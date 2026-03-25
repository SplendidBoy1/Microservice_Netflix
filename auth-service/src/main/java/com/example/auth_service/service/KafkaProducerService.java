package com.example.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.auth_service.dto.EventCreatedUserKafka;

@Service
public class KafkaProducerService {
    
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessage(EventCreatedUserKafka message){
        kafkaTemplate.send("create-user", message);
    }

    

}
