package com.scaler.cartservice;

import com.scaler.cartservice.advices.GlobalExceptionHandler;
import com.scaler.cartservice.exceptions.CartNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleCartNotFoundException_shouldReturnNotFound() {
        CartNotFoundException exception = new CartNotFoundException("Cart not found");
        ResponseEntity<Map<String, String>> response = handler.handleCartNotFoundException(exception);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Cart not found", response.getBody().get("error"));
    }

    @Test
    void handleGenericException_shouldReturnInternalServerError() {
        ResponseEntity<Map<String, String>> response = handler.handleGenericException(new RuntimeException("Unexpected error"));

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("An unexpected error occurred: Unexpected error", response.getBody().get("error"));
    }
}
