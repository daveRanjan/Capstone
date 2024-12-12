package com.scaler.notification.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @KafkaListener(topics = "capstone-notification-topic")
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
