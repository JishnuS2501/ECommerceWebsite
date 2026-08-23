package com.example.ecommercewebsite.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    @ManyToOne
    private Category category;
}
