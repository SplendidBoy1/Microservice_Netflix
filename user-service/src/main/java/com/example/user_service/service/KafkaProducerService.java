package com.example.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.user_service.dto.EventDeletedUserKafka;

@Service
public class KafkaProducerService {
    
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @TransactionalEventListener(phase = 
        TransactionPhase.AFTER_COMMIT)
    public void deteleUserMessage(EventDeletedUserKafka message){
        kafkaTemplate.send("delete-user", message);
    }

}
