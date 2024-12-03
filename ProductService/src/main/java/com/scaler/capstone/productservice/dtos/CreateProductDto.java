package com.scaler.capstone.productservice.dtos;

import com.scaler.capstone.productservice.entities.Product;
import lombok.Data;

@Data
public class CreateProductDto {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;


    public static CreateProductDto from(Product product){
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setTitle(product.getTitle());
        createProductDto.setDescription(product.getDescription());
        createProductDto.setPrice(product.getPrice());
        createProductDto.setImageUrl(product.getImageUrl());
        createProductDto.setCategoryName(product.getCategory().getName());
        return createProductDto;
    }


    public Product toEntity(){
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        return product;
    }

    public Product toEntity(Product existingProduct){
        if (this.title != null) {
            existingProduct.setTitle(this.title);
        }
        if (this.description != null) {
            existingProduct.setDescription(this.description);
        }
        if (this.price != null && this.price > 0L) {
            existingProduct.setPrice(this.price);
        }

        if (this.imageUrl != null) {
            existingProduct.setImageUrl(this.imageUrl);
        }
        return existingProduct;
    }
}
