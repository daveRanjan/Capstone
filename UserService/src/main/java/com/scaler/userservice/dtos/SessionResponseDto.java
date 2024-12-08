package com.scaler.userservice.dtos;

import com.scaler.userservice.entities.Session;

public class SessionResponseDto {
    private Session session;

    public SessionResponseDto() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
