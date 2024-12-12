package com.scaler.paymentservice.dtos;

public enum EventPurpose {
    PAYMENT_INITIATED("payment-initiated"),
    PAYMENT_SUCCESSFUL("payment-successful"),
    PAYMENT_FAILED("payment-failed");;

    private String value;

    EventPurpose(String value) {
        this.value = value;
    }
}
