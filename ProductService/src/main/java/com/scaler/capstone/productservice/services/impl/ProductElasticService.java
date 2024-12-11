package com.scaler.capstone.productservice.services.impl;

import com.scaler.capstone.productservice.entities.Product;
import com.scaler.capstone.productservice.entities.ProductElastic;
import com.scaler.capstone.productservice.repositories.elastic.ProductElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductElasticService {

    @Autowired
    ProductElasticRepository productElasticRepository;

    public void save(Product product) {
        productElasticRepository.save(new ProductElastic(product));
    }


}
