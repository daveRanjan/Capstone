package com.scaler.cartservice.services;

import com.scaler.cartservice.entities.CartItem;
import com.scaler.cartservice.exceptions.CartNotFoundException;
import com.scaler.cartservice.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CartService(CartRepository cartRepository, RedisTemplate<String, Object> redisTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.cartRepository = cartRepository;
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<CartItem> getCart(String userId) {
        // Fetch from Redis cache first
        String cacheKey = "cart:" + userId;
        List<CartItem> cachedCart = (List<CartItem>) redisTemplate.opsForValue().get(cacheKey);
        if (cachedCart != null) {
            return cachedCart;
        }

        List<CartItem> cartItems = cartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new CartNotFoundException("No cart found for user: " + userId);
        }

        redisTemplate.opsForValue().set(cacheKey, cartItems);
        return cartItems;
    }

    public CartItem addToCart(CartItem item) {
        CartItem savedItem = cartRepository.save(item);
        kafkaTemplate.send("cart-events", "Product added to cart: " + item.getProductName());
        redisTemplate.delete("cart:" + item.getUserId()); // Invalidate cache
        return savedItem;
    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
        redisTemplate.delete("cart:" + userId);
        kafkaTemplate.send("cart-events", "Cart cleared for user: " + userId);
    }
}
