package com.scaler.orderservice.dtos;

import com.scaler.orderservice.entities.Order;

import java.time.LocalDateTime;

public class OrderResponseDto {
    private Long id;               // ID of the order
    private String status;         // Status of the order (e.g., PENDING, CONFIRMED)
    private String productDetails; // JSON string containing product details
    private LocalDateTime createdAt; // Timestamp when the order was created
    private LocalDateTime updatedAt; // Timestamp when the order was last updated

    // Constructor to map from the Order entity
    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.productDetails = order.getProductDetails();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }

    public OrderResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
