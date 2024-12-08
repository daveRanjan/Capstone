package com.scaler.capstone.productservice.services.impl;

import com.scaler.capstone.productservice.dtos.CreateProductDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.entities.Category;
import com.scaler.capstone.productservice.entities.Product;
import com.scaler.capstone.productservice.repositories.CategoryRepository;
import com.scaler.capstone.productservice.repositories.ProductRepository;
import com.scaler.capstone.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "product.service.type", havingValue = "db")
public class ProductServiceDbImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public GetProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return GetProductDto.from(product);
    }

    @Override
    public List<GetProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream().map(GetProductDto::from).toList();
    }

    @Override
    public GetProductDto createProduct(CreateProductDto createProductDto) {
        Category category = categoryRepository.findByName(createProductDto.getCategory());
        if (category == null) {
            category = new Category();
            category.setName(createProductDto.getCategory());
        }
        Product product = createProductDto.toEntity();
        product.setCategory(category);
        return GetProductDto.from(productRepository.save(product));

    }

    @Override
    public GetProductDto updateProduct(Long productId, CreateProductDto updateProductDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Category category = categoryRepository.findByName(updateProductDto.getCategory());
        if (category == null) {
            category = new Category();
            category.setName(updateProductDto.getCategory());
        }
        existingProduct = updateProductDto.toEntity(existingProduct);
        if (StringUtils.hasText(updateProductDto.getCategory())) {
            existingProduct.setCategory(category);
        }
        return GetProductDto.from(productRepository.save(existingProduct));
    }
}
