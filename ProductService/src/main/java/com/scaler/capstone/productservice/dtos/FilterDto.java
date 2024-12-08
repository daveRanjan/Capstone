package com.scaler.capstone.productservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    private String key;
    private List<String> values;
}
