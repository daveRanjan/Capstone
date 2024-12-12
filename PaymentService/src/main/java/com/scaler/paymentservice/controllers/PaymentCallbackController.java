package com.scaler.paymentservice.controllers;

import com.scaler.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments/callback")
public class PaymentCallbackController {

    PaymentService paymentService;

    public PaymentCallbackController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public void stripeWebhook(@RequestBody Event event) throws StripeException {
        paymentService.stripeCallback(event);
    }

    @GetMapping
    public void stripeCallback(@RequestParam String session) throws StripeException {
        paymentService.stripeCallback(session);
    }

}
