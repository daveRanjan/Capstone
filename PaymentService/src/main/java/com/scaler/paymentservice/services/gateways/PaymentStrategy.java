package com.scaler.paymentservice.services.gateways;

import org.springframework.stereotype.Service;

@Service
public class PaymentStrategy {

    StripePaymentGateway stripePaymentGateway;

    public PaymentGateway getBestPaymentGateway() {
        return stripePaymentGateway;
    }
}
