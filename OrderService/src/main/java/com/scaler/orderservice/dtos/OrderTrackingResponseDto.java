package com.scaler.orderservice.dtos;

import com.scaler.orderservice.entities.Order;

import java.time.LocalDateTime;

public class OrderTrackingResponseDto {
    private Long orderId;
    private String status;
    private String productDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderTrackingResponseDto(Order order) {
        this.orderId = order.getId();
        this.status = order.getStatus();
        this.productDetails = order.getProductDetails();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }

    public OrderTrackingResponseDto() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
