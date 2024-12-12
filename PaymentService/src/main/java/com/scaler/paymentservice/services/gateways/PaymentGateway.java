package com.scaler.paymentservice.services.gateways;

import com.scaler.paymentservice.dtos.PaymentLinkLocal;

public interface PaymentGateway {

    PaymentLinkLocal generatePaymentLink(String orderId, String email, String phoneNumber, Long amount);
}
