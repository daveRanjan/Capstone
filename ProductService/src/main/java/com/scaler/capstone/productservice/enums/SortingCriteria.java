package com.scaler.capstone.productservice.enums;

import lombok.Getter;

@Getter
public enum SortingCriteria {
    PRICE_LOW_TO_HIGH("price", "asc"),
    PRICE_HIGH_TO_LOW("price", "desc");

    private final String key;
    private final String order;

    SortingCriteria(String key, String order) {
        this.key = key;
        this.order = order;
    }

}
