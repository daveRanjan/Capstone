package com.scaler.capstone.productservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Getter
@Setter
@Document(indexName = "product")
public class ProductElastic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Long sql_id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String category;

    public ProductElastic(){

    }

    public ProductElastic(Product product){
        this.id = UUID.randomUUID().toString();
        this.sql_id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.category = product.getCategory().getName();
    }
}
