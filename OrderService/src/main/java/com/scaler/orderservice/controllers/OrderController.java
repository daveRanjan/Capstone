package com.scaler.orderservice.controllers;

import com.scaler.orderservice.dtos.OrderRequestDto;
import com.scaler.orderservice.dtos.OrderResponseDto;
import com.scaler.orderservice.dtos.OrderTrackingResponseDto;
import com.scaler.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/{userId}/history")
    public List<OrderResponseDto> getOrderHistory(@PathVariable Long userId) {
        return orderService.getOrderHistory(userId);
    }

    @GetMapping("/{orderId}/tracking")
    public OrderTrackingResponseDto trackOrder(@PathVariable Long orderId) {
        return orderService.trackOrder(orderId);
    }

    @PutMapping("/{orderId}/status")
    public void updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
    }
}
