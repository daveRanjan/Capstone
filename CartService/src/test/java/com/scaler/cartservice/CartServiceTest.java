package com.scaler.cartservice;

import com.scaler.cartservice.entities.CartItem;
import com.scaler.cartservice.exceptions.CartNotFoundException;
import com.scaler.cartservice.repositories.CartRepository;
import com.scaler.cartservice.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartService(cartRepository, redisTemplate, kafkaTemplate);
    }

    @Test
    void getCart_shouldReturnCartFromCache() {
        String userId = "user123";
        List<CartItem> mockCart = List.of(new CartItem());
        when(redisTemplate.opsForValue().get("cart:" + userId)).thenReturn(mockCart);

        List<CartItem> result = cartService.getCart(userId);

        assertEquals(mockCart, result);
        verify(redisTemplate, times(1)).opsForValue().get("cart:" + userId);
        verifyNoInteractions(cartRepository);
    }

    @Test
    void getCart_shouldReturnCartFromDatabase() {
        String userId = "user123";
        List<CartItem> mockCart = List.of(new CartItem());
        when(redisTemplate.opsForValue().get("cart:" + userId)).thenReturn(null);
        when(cartRepository.findByUserId(userId)).thenReturn(mockCart);

        List<CartItem> result = cartService.getCart(userId);

        assertEquals(mockCart, result);
        verify(cartRepository, times(1)).findByUserId(userId);
        verify(redisTemplate, times(1)).opsForValue().set("cart:" + userId, mockCart);
    }

    @Test
    void getCart_shouldThrowExceptionIfCartNotFound() {
        String userId = "user123";
        when(redisTemplate.opsForValue().get("cart:" + userId)).thenReturn(null);
        when(cartRepository.findByUserId(userId)).thenReturn(List.of());

        assertThrows(CartNotFoundException.class, () -> cartService.getCart(userId));
    }

    @Test
    void addToCart_shouldSaveItemAndPublishEvent() {
        CartItem item = new CartItem();
        item.setUserId("user123");
        when(cartRepository.save(item)).thenReturn(item);

        CartItem result = cartService.addToCart(item);

        assertEquals(item, result);
        verify(cartRepository, times(1)).save(item);
        verify(kafkaTemplate, times(1)).send("cart-events", "Product added to cart: null");
        verify(redisTemplate, times(1)).delete("cart:" + item.getUserId());
    }

    @Test
    void clearCart_shouldClearCartAndPublishEvent() {
        String userId = "user123";
        List<CartItem> cartItems = List.of(new CartItem());
        when(cartRepository.findByUserId(userId)).thenReturn(cartItems);

        cartService.clearCart(userId);

        verify(cartRepository, times(1)).deleteByUserId(userId);
        verify(redisTemplate, times(1)).delete("cart:" + userId);
        verify(kafkaTemplate, times(1)).send("cart-events", "Cart cleared for user: " + userId);
    }

    @Test
    void clearCart_shouldThrowExceptionIfCartNotFound() {
        String userId = "user123";
        when(cartRepository.findByUserId(userId)).thenReturn(List.of());

        assertThrows(CartNotFoundException.class, () -> cartService.clearCart(userId));
    }
}
