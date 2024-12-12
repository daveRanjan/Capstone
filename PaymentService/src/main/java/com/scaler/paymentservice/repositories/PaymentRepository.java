package com.scaler.paymentservice.repositories;

import com.scaler.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findFirstByPaymentLink(String paymentLink);
}
