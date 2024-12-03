package com.scaler.capstone.productservice.dtos;

import com.scaler.capstone.productservice.entities.Product;
import lombok.Data;

@Data
public class GetProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;

    public static GetProductDto from(Product product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(product.getId());
        getProductDto.setTitle(product.getTitle());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setPrice(product.getPrice());
        getProductDto.setImageUrl(product.getImageUrl());
        getProductDto.setCategoryName(product.getCategory().getName());
        return getProductDto;
    }
}
