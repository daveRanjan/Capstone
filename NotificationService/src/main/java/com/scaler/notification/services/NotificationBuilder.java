package com.scaler.notification.services;

import com.scaler.notification.dto.EventMessageDto;
import com.scaler.notification.dto.UserDto;
import com.scaler.notification.entities.Notification;
import com.scaler.notification.feigns.UserServiceFeign;
import com.scaler.notification.utils.Purposes;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationBuilder {

    private UserServiceFeign userServiceFeign;
    private TemplateService templateService;

    public NotificationBuilder(UserServiceFeign userServiceFeign, TemplateService templateService) {
        this.userServiceFeign = userServiceFeign;
        this.templateService = templateService;
    }

    public Notification buildNotification(EventMessageDto eventMessageDto) {
        UserDto userDetails = userServiceFeign.getUserDetails(eventMessageDto.getUserId());

        Map<String, Object> context = eventMessageDto.getContext();
        Notification notification = new Notification();
        switch (context.get("purpose").toString()) {
            case "ORDER_PLACED":
                notification = createOrderPlacedNotification(userDetails, context);
                break;
            case "REGISTRATION_CONFIRMATION":
                notification = createRegistrationConfirmationNotification(userDetails, context);
                break;
            case "PAYMENT_CONFIRMATION":
                notification = createPaymentConfirmationNotification(userDetails, context);
                break;
            default:
                return notification;
        }
    }

    private Notification createPaymentConfirmationNotification(UserDto userDetails, Map<String, Object> context) {
        Notification notification = new Notification();
        notification.setRecipient(userDetails.getEmail());
        notification.setSubject("Payment Confirmation");
        notification.setMessage(templateService.generateEmail("payment-confirmation", context));
        notification.setPurpose(Purposes.PAYMENT_CONFIRMATION);
        return notification;
    }

    private Notification createRegistrationConfirmationNotification(UserDto userDetails, Map<String, Object> context) {
        Notification notification = new Notification();
        notification.setRecipient(userDetails.getEmail());
        notification.setSubject("Registration Confirmation");
        notification.setMessage(templateService.generateEmail("registration-confirmation", context));
        notification.setPurpose(Purposes.REGISTRATION_CONFIRMATION);
        return notification;
    }

    private Notification createOrderPlacedNotification(UserDto userDetails, Map<String, Object> context) {
        Notification notification = new Notification();
        notification.setRecipient(userDetails.getEmail());
        notification.setSubject("Order Placed");
        notification.setMessage(templateService.generateEmail("order-placed", context));
        notification.setPurpose(Purposes.ORDER_PLACED);
        return notification;
    }

}
