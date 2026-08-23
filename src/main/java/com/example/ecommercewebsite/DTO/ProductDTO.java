package com.example.ecommercewebsite.DTO;

import com.example.ecommercewebsite.Model.BaseModel;
import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private Category category;
    private Status status;
}
