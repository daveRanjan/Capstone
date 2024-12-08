package com.scaler.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "reset_password_token")
public class ResetPasswordToken {
    @Id
    private String token;
    private Integer userId;
    @Column(insertable = false, updatable = false)
    private Date expirayAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getExpirayAt() {
        return expirayAt;
    }
}
