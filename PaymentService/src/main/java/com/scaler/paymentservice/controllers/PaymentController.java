package com.scaler.paymentservice.controllers;

import com.scaler.paymentservice.dtos.InitiatePaymentRequestDto;
import com.scaler.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) {
        return paymentService.initiatePayment(requestDto);
    }
}
