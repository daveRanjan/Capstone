package com.scaler.paymentservice.services.gateways;

import org.springframework.stereotype.Service;

@Service
public class PaymentStrategy {

    StripePaymentGateway stripePaymentGateway;

    public PaymentStrategy(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway() {
        return stripePaymentGateway;
    }
}
