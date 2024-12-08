package com.scaler.orderservice.services;

import com.scaler.orderservice.dtos.OrderRequestDto;
import com.scaler.orderservice.dtos.OrderResponseDto;
import com.scaler.orderservice.dtos.OrderTrackingResponseDto;
import com.scaler.orderservice.entities.Order;
import com.scaler.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequest) {
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setProductDetails(orderRequest.getProductDetails());
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return new OrderResponseDto(savedOrder);
    }

    public List<OrderResponseDto> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    public OrderTrackingResponseDto trackOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderTrackingResponseDto(order);
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }
}
