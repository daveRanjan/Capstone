package com.scaler.capstone.productservice.dtos;

import com.scaler.capstone.productservice.entities.Product;
import lombok.Data;

@Data
public class CreateProductDto {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;


    public static CreateProductDto from(Product product){
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setTitle(product.getTitle());
        createProductDto.setDescription(product.getDescription());
        createProductDto.setPrice(product.getPrice());
        createProductDto.setImage(product.getImageUrl());
        createProductDto.setCategory(product.getCategory().getName());
        return createProductDto;
    }


    public Product toEntity(){
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.image);
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

        if (this.image != null) {
            existingProduct.setImageUrl(this.image);
        }
        return existingProduct;
    }
}
