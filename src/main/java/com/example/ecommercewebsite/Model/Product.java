package com.example.ecommercewebsite.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Product {
    private String name;
    private String description;
    private double price;
    private Category category;
    private Map<String, String> specifications;
}
