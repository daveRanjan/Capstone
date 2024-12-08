package com.scaler.cartservice;

import com.scaler.cartservice.controllers.CartController;
import com.scaler.cartservice.entities.CartItem;
import com.scaler.cartservice.services.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService cartService;

    private CartController cartController;

    @Test
    void getCart_shouldReturnCartItems() {
        MockitoAnnotations.openMocks(this);
        cartController = new CartController(cartService);

        String userId = "user123";
        List<CartItem> mockCart = List.of(new CartItem());
        when(cartService.getCart(userId)).thenReturn(mockCart);

        ResponseEntity<List<CartItem>> response = cartController.getCart(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCart, response.getBody());
    }

    @Test
    void addToCart_shouldAddItemAndReturn() {
        CartItem item = new CartItem();
        when(cartService.addToCart(item)).thenReturn(item);

        ResponseEntity<CartItem> response = cartController.addToCart(item);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(item, response.getBody());
    }

    @Test
    void clearCart_shouldClearCartAndReturnNoContent() {
        String userId = "user123";

        ResponseEntity<Void> response = cartController.clearCart(userId);

        assertEquals(204, response.getStatusCodeValue());
        verify(cartService, times(1)).clearCart(userId);
    }
}
