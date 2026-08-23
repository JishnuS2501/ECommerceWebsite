package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Product;

public interface IProductService {
    public Product getProductById(long id);

    public Product putProductById(long productId, Product product);
}
