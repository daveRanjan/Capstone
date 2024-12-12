package com.scaler.paymentservice.services.gateways;

import com.scaler.paymentservice.dtos.PaymentLinkLocal;
import com.scaler.paymentservice.repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.secret.key}")
    private String apiKey;
    private PaymentRepository paymentRepository;

    @Override
    public PaymentLinkLocal generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", amount);
        params.put("currency", "inr");
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "Burnol");
        params.put("product_data", productData);

        Price price = null;
        try {
            price = Price.create(params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Object> lineItems = new ArrayList<>();
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put(
                "price",
                price.getId()
        );
        lineItem1.put("quantity", 1);
        lineItems.add(lineItem1);
        params = new HashMap<>();
        params.put("line_items", lineItems);

        Map<String, Object> afterCompletion = new HashMap<>();
        afterCompletion.put("type", "redirect");

        Map<String, Object> redirect = new HashMap<>();
        redirect.put("url", "https://eodolnl4c5lrs2.m.pipedream.net?session={CHECKOUT_SESSION_ID}");
        afterCompletion.put("redirect", redirect);

        params.put("after_completion", afterCompletion);

        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new PaymentLinkLocal(paymentLink.getId(), paymentLink.getUrl());
    }
}
