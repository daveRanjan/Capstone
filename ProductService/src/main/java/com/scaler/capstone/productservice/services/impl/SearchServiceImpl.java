package com.scaler.capstone.productservice.services.impl;

import com.scaler.capstone.productservice.dtos.FilterDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.entities.ProductElastic;
import com.scaler.capstone.productservice.enums.SortingCriteria;
import com.scaler.capstone.productservice.repositories.elastic.ProductElasticRepository;
import com.scaler.capstone.productservice.repositories.jpa.ProductRepository;
import com.scaler.capstone.productservice.services.ProductSpecification;
import com.scaler.capstone.productservice.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ProductRepository productRepository;
    private final ProductElasticRepository productElasticRepository;

    @Override
    public Page<GetProductDto> simpleSearch(String query, List<FilterDto> filters, SortingCriteria sortCriteria, Integer page, Integer size) {
        ProductSpecification productSpecification = new ProductSpecification(filters, sortCriteria, query);
        return productRepository.findAll(productSpecification, PageRequest.of(page, size)).map(GetProductDto::from);
    }

    @Override
    public Page<GetProductDto> elasticSearch(String query, List<FilterDto> filters, SortingCriteria sortCriteria, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.asc("title"));

        if(sortCriteria != null && sortCriteria.getKey() != null && sortCriteria.getOrder() != null){
            sort = sortCriteria.getOrder().equalsIgnoreCase("ASC") ?
                    Sort.by(Sort.Order.asc(sortCriteria.getKey())) :
                    Sort.by(Sort.Order.desc(sortCriteria.getKey()));
        }
        Page<ProductElastic> result = productElasticRepository.findAllByTitleOrDescriptionOrCategory(query, query, query,PageRequest.of(page, size, sort));
        return result.map(GetProductDto::from);
    }
}
