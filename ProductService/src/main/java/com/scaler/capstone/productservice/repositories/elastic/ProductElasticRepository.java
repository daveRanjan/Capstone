package com.scaler.capstone.productservice.repositories.elastic;

import com.scaler.capstone.productservice.entities.ProductElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductElasticRepository extends ElasticsearchRepository<ProductElastic, Long> {

    Page<ProductElastic> findAllByTitleOrDescriptionOrCategory(String title, String description, String category, Pageable pageable);
}
