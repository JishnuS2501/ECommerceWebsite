package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.State;
import com.example.ecommercewebsite.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product getProductById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public Product replaceProductById(long productId, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("No product with id "+ productId + " found");
        }
        product.setId(productId);
        return productRepository.save(product);
    }

    @Override
    public Product deleteProductById(long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product with id "+ productId +" not found to delete");
        }
        productRepository.delete(optionalProduct.get());
        return new Product();
    }

    @Override
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @Override
    public Product updateProductById(long productId, Product product){
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()){
            throw new RuntimeException("Product with id "+product.getId()+" already exists.");
        }
        return productRepository.save(product);
    }
}
