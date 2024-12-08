package com.scaler.userservice.controller;

import com.scaler.userservice.dtos.CreateUserRequestDto;
import com.scaler.userservice.dtos.ForgotPasswordRequestDto;
import com.scaler.userservice.dtos.LoginUserRequestDto;
import com.scaler.userservice.dtos.ResetPasswordRequestDto;
import com.scaler.userservice.entities.Session;
import com.scaler.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Session> login(@RequestBody LoginUserRequestDto loginUserRequest) {
        Session session = authService.login(loginUserRequest.getEmail(), loginUserRequest.getPassword());
        return ResponseEntity.ok(session);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody CreateUserRequestDto createUserRequest) {
        authService.signUp(createUserRequest.getEmail(), createUserRequest.getPassword(), createUserRequest.getName());
        return ResponseEntity.status(201).build();
        //CREATED()
    }

    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordRequestDto forgotPasswordRequest) {
        authService.forgotPassword(forgotPasswordRequest);
        return ResponseEntity.ok(Map.of("message", "Password reset link sent to your email"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Session> resetPassword(@RequestBody ResetPasswordRequestDto resetPasswordRequest) {
        Session session = authService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok(session);
    }

    @DeleteMapping("/logout/{sessionId}")
    public ResponseEntity<Void> logout(@PathVariable String sessionId) {
        authService.logout(sessionId);
        return ResponseEntity.noContent().build();
    }
}
