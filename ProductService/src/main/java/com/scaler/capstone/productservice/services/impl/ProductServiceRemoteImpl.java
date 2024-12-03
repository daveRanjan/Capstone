package com.scaler.capstone.productservice.services.impl;

import com.scaler.capstone.productservice.dtos.CreateProductDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.services.ProductService;
import com.scaler.capstone.productservice.services.feins.RemoteProductsFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "product.service.type", havingValue = "remote", matchIfMissing = true)
public class ProductServiceRemoteImpl implements ProductService {


    private final RemoteProductsFeign remoteProductsFeign;

    @Override
    public GetProductDto getProduct(Long productId) {
        return remoteProductsFeign.getProduct(productId);
    }

    @Override
    public List<GetProductDto> getAllProducts() {
        return remoteProductsFeign.getAllProduct();
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
