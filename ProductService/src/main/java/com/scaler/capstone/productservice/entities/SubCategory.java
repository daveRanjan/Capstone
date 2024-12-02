package com.scaler.capstone.productservice.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SubCategory extends BaseEntity {
    private String name;
}
