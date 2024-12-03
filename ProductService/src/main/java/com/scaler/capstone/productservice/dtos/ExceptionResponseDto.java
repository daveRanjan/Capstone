package com.scaler.capstone.productservice.dtos;

import lombok.Builder;

@Builder
public class ExceptionResponseDto {
    private String message;
    private String description;
    private String timestamp;
    private String path;
    private String exception;
}
