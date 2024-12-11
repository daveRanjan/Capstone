package com.scaler.capstone.productservice.dtos;

import com.scaler.capstone.productservice.entities.Product;
import com.scaler.capstone.productservice.entities.ProductElastic;
import lombok.Data;

@Data
public class GetProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;

    public static GetProductDto from(Product product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(product.getId());
        getProductDto.setTitle(product.getTitle());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setPrice(product.getPrice());
        getProductDto.setImage(product.getImageUrl());
        getProductDto.setCategory(product.getCategory().getName());
        return getProductDto;
    }
    public static GetProductDto from(ProductElastic product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(product.getSql_id());
        getProductDto.setTitle(product.getTitle());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setPrice(product.getPrice());
        getProductDto.setImage(product.getImageUrl());
        getProductDto.setCategory(product.getCategory());
        return getProductDto;
    }
}
