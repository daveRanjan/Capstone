package com.scaler.capstone.productservice.services;

import com.scaler.capstone.productservice.dtos.CreateProductDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;

import java.util.List;

public interface ProductService {
    GetProductDto getProduct(Long productId);

    List<GetProductDto> getAllProducts();

    GetProductDto createProduct(CreateProductDto createProductDto);


    GetProductDto updateProduct(Long productId, CreateProductDto updateProductDto);
}
