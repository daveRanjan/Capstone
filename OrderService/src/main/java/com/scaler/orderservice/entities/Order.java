package com.scaler.orderservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String productDetails; // JSON String
    private String status; // e.g., PENDING, CONFIRMED, SHIPPED
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
