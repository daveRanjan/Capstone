package com.scaler.capstone.productservice.advice;

import com.scaler.capstone.productservice.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(IllegalArgumentException e) {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage("Incorrect Payload provided");
        dto.setDescription(e.getMessage());
        dto.setTimestamp(new Date().toString());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException(RuntimeException e) {
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setMessage("Internal Server Error");
        dto.setDescription(e.getMessage());
        dto.setTimestamp(new Date().toString());
        return dto;
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "something went wrong";
    }
}
