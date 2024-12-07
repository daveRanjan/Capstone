package com.scaler.userservice.dtos;

public class ResetPasswordRequestDto {
    String token;
    String password;

    public ResetPasswordRequestDto() {
    }

    public ResetPasswordRequestDto(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
