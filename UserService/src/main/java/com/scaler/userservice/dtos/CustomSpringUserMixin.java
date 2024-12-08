package com.scaler.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public abstract class CustomSpringUserMixin {
    @JsonCreator
    public CustomSpringUserMixin(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("authorities") Collection<?> authorities
    ) {}
}
