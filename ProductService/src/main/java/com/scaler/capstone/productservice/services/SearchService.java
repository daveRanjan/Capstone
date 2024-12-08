package com.scaler.capstone.productservice.services;

import com.scaler.capstone.productservice.dtos.FilterDto;
import com.scaler.capstone.productservice.dtos.GetProductDto;
import com.scaler.capstone.productservice.enums.SortingCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {
    Page<GetProductDto> simpleSearch(String q, List<FilterDto> filters, SortingCriteria sortCriteria, Integer page, Integer size);
}
