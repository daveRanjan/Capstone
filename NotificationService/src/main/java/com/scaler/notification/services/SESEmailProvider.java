package com.scaler.notification.services;

import com.scaler.notification.entities.Notification;
import org.springframework.stereotype.Service;

@Service
public class SESEmailProvider implements EmailProvider {
    @Override
    public void sendEmail(Notification notification) {

    }
}
