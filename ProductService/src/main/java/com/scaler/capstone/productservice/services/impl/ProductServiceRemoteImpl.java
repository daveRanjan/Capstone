package com.scaler.capstone.productservice.services.impl;

import com.scaler.capstone.productservice.dtos.CreateProductDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.services.ProductService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "product.service.type", havingValue = "remote", matchIfMissing = true)
public class ProductServiceRemoteImpl implements ProductService {



    @Override
    public GetProductDto getProduct(Long productId) {
        return null;
    }

    @Override
    public List<GetProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public GetProductDto createProduct(CreateProductDto createProductDto) {
        return null;
    }

    @Override
    public GetProductDto updateProduct(Long productId, CreateProductDto updateProductDto) {
        return null;
    }
}
