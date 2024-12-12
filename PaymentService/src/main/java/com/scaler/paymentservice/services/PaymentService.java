package com.scaler.paymentservice.services;

import com.scaler.paymentservice.dtos.EventPurpose;
import com.scaler.paymentservice.dtos.InitiatePaymentRequestDto;
import com.scaler.paymentservice.dtos.PaymentLinkLocal;
import com.scaler.paymentservice.entities.Payment;
import com.scaler.paymentservice.repositories.PaymentRepository;
import com.scaler.paymentservice.services.gateways.PaymentGateway;
import com.scaler.paymentservice.services.gateways.PaymentStrategy;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.gson.JsonObject;

import java.util.Map;

@Service
public class PaymentService {

    PaymentStrategy paymentStrategy;
    KafkaService kafkaService;
    PaymentRepository paymentRepository;

    public PaymentService(PaymentStrategy paymentStrategy, KafkaService kafkaService, PaymentRepository paymentRepository) {
        this.paymentStrategy = paymentStrategy;
        this.kafkaService = kafkaService;
        this.paymentRepository = paymentRepository;
    }

    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) {
        Long amount = 1000L;
        PaymentGateway gateway = paymentStrategy.getBestPaymentGateway();
        PaymentLinkLocal result = gateway.generatePaymentLink("Test Order Id", "testlocal@yopmail.com", "+919911662332", amount);
        paymentRepository.save(new Payment(Long.parseLong(requestDto.getOrderId()), Long.parseLong(requestDto.getUserId()), amount, "PAYMENT_INITIATED", result.getId()));
        return result.getUrl();
    }

    public void stripeCallback(Event event) throws StripeException {
        if (event.getType().equalsIgnoreCase("checkout.session.completed")
                && event.getDataObjectDeserializer().getObject().isPresent()) {
            stripeCallback(event.getDataObjectDeserializer().getObject().get().getRawJsonObject().getAsJsonPrimitive("id").getAsString());
        }
    }

    public void stripeCallback(String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);
        Payment payment = paymentRepository.findFirstByPaymentLink(session.getPaymentLink());
        if (session.getPaymentStatus().equals("paid")) {
            payment.setStatus("PAYMENT_SUCCESSFUL");
            paymentRepository.save(payment);
            kafkaService.sendMessage(Map.of("user_id", 1, "email", "test@yopmail.com", "name", "test", "purpose", EventPurpose.PAYMENT_SUCCESSFUL));
        } else {
            payment.setStatus("PAYMENT_FAILED");
            paymentRepository.save(payment);
            kafkaService.sendMessage(Map.of("user_id", 1, "email", "test@yopmail.com", "name", "test", "purpose", EventPurpose.PAYMENT_FAILED));
        }
    }
}
