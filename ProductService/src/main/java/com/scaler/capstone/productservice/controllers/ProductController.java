package com.scaler.capstone.productservice.controllers;

import com.scaler.capstone.productservice.dtos.CreateProductDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public GetProductDto getProducts(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }


    @GetMapping
    public List<GetProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public GetProductDto createProduct(@RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto);
    }

    @PatchMapping("/{productId}")
    public GetProductDto updateProduct(@PathVariable Long productId, @RequestBody CreateProductDto updateProductDto) {
        return productService.updateProduct(productId, updateProductDto);
    }
}
