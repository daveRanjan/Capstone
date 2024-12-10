package com.scaler.paymentservice.services.gateways;

public interface PaymentGateway {

    String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount);
}
