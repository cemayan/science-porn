package com.cayan.contentservice.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic}")
    public void listenEarthQuakes(@Payload String msg) {

        try {
            System.out.println(msg);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

  
}
