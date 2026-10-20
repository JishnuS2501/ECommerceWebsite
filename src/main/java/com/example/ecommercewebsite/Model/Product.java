package com.example.ecommercewebsite.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Map;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
