package com.scaler.userservice.controller;

import com.scaler.userservice.dtos.CreateUserRequestDto;
import com.scaler.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody CreateUserRequestDto createUserRequest) {
        authService.signUp(createUserRequest.getEmail(), createUserRequest.getPassword(), createUserRequest.getName());
        return ResponseEntity.status(201).build();
    }

}
