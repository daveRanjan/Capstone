package com.scaler.paymentservice.services;

import com.scaler.paymentservice.dtos.InitiatePaymentRequestDto;
import com.scaler.paymentservice.services.gateways.PaymentGateway;
import com.scaler.paymentservice.services.gateways.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentService {

    PaymentStrategy paymentStrategy;

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) {
        PaymentGateway gateway = paymentStrategy.getBestPaymentGateway();
        return gateway.generatePaymentLink(null, null, null, null);
    }
}
