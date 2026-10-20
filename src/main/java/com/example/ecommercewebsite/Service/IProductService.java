package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(long id);

    Product replaceProductById(long productId, Product product);

    Product deleteProductById(long productId);

    List<Product> getAllProduct();

    Product updateProductById(long productId, Product product);

    Product addProduct(Product product);
}
