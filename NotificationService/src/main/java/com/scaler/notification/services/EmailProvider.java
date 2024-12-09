package com.scaler.notification.services;

import com.scaler.notification.entities.Notification;

public interface EmailProvider {
    void sendEmail(Notification notification);
}
