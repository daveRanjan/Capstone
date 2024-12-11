package com.scaler.userservice.utils;

public enum EventPurpose {
    USER_CREATED("user-created"),
    USER_UPDATED("user-updated"),
    USER_DELETED("user-deleted"),
    PASSWORD_RESET("password-reset");

    private final String topic;

    EventPurpose(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
