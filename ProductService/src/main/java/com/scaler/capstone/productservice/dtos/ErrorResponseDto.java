package com.scaler.capstone.productservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDto {
    private String message;
    private String description;
    private String timestamp;
    private String path;
    private String exception;
}
