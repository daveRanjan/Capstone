package com.scaler.capstone.productservice.services.impl;

import com.scaler.capstone.productservice.dtos.FilterDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.enums.SortingCriteria;
import com.scaler.capstone.productservice.repositories.ProductRepository;
import com.scaler.capstone.productservice.services.ProductSpecification;
import com.scaler.capstone.productservice.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ProductRepository productRepository;

    @Override
    public Page<GetProductDto> simpleSearch(String query, List<FilterDto> filters, SortingCriteria sortCriteria, Integer page, Integer size) {
        ProductSpecification productSpecification = new ProductSpecification(filters, sortCriteria, query);
        return productRepository.findAll(productSpecification, PageRequest.of(page, size)).map(GetProductDto::from);
    }
}
