package com.scaler.userservice.dtos;

public class LoginUserRequestDto {
    private String email;
    private String password;

    public LoginUserRequestDto() {
    }

    public LoginUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
