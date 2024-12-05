package com.scaler.capstone.productservice.services.feins;

import com.scaler.capstone.productservice.dtos.GetProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "products-service", url = "${fein.products.service.url}")
public interface RemoteProductsFeign {

    @GetMapping(value = "/products/{productId}")
    GetProductDto getProduct(Long productId);

    @GetMapping(value = "/products")
    List<GetProductDto> getAllProduct();

    @PostMapping(value = "/products")
    GetProductDto createProduct(@RequestBody GetProductDto createProductDto);

    @PatchMapping(value = "/products/{productId}")
    GetProductDto updateProduct(@PathVariable Long productId, @RequestBody GetProductDto updateProductDto);

}
