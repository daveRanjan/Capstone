package com.scaler.capstone.productservice.controllers;

import com.scaler.capstone.productservice.dtos.CreateProductDto;
import com.scaler.capstone.productservice.dtos.FilterDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.enums.SortingCriteria;
import com.scaler.capstone.productservice.services.ProductService;
import com.scaler.capstone.productservice.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final SearchService searchService;

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

    @GetMapping("/search")
    public Page<GetProductDto> search(@RequestParam(name = "q", required = true) String q,
                                      @RequestParam(name = "filters", required = false) List<FilterDto> filters,
                                      @RequestParam(name = "sort", required = false) SortingCriteria sortCriteria,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return searchService.elasticSearch(q, filters, sortCriteria, page, size);
    }
}
