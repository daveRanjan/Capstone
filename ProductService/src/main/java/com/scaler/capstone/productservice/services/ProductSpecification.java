package com.scaler.capstone.productservice.services;

import com.scaler.capstone.productservice.dtos.FilterDto;
import com.scaler.capstone.productservice.entities.Product;
import com.scaler.capstone.productservice.enums.SortingCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private final List<FilterDto> filters;
    private final SortingCriteria sortingCriteria;
    private final String query;


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> allPredicates = new ArrayList<>();

        // Add search predicate
        if (query != null) {
            // get search predicatae
            allPredicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%" + query + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + query + "%"),
                    criteriaBuilder.like(root.join("category").get("name"), "%" + query + "%")));
        }

        // Add filter predicates
        for (FilterDto filter : filters) {
            // get filter predicate
            allPredicates.add(criteriaBuilder.in(root.get(filter.getKey())).value(filter.getValues()));
        }

        // Add sorting predicate
        if (sortingCriteria.getOrder().equalsIgnoreCase("ASC")) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortingCriteria.getKey())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortingCriteria.getKey())));
        }
        criteriaQuery.distinct(Boolean.TRUE);

        if (!allPredicates.isEmpty()) {
            return criteriaBuilder.and(allPredicates.toArray(Predicate[]::new));
        }
        return null;
    }

}
