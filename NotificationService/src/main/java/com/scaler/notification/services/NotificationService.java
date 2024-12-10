package com.scaler.notification.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.notification.dto.EventMessageDto;
import com.scaler.notification.entities.Notification;
import com.scaler.notification.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailProvider emailProvider;

    @KafkaListener(topics = "notification_topic", groupId = "notification_group")
    public void handleNotification(String message) throws JsonProcessingException {
        // Parse and save notification
        Notification notification = parseMessage(message);
        notificationRepository.save(notification);

        // Send email
        try {
            emailProvider.sendEmail(notification);
            notification.setStatus("SENT");
        } catch (Exception e) {
            notification.setStatus("FAILED");
        }
        notificationRepository.save(notification);
    }

    private Notification parseMessage(String message) throws JsonProcessingException {
        EventMessageDto eventMessageDto = new ObjectMapper().readValue(message, EventMessageDto.class);


        return null;
    }
}
