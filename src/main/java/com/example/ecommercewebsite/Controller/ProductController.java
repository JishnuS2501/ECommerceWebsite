package com.example.ecommercewebsite.Controller;


import com.example.ecommercewebsite.DTO.ProductDTO;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.Status;
import com.example.ecommercewebsite.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/product/{id}")
    public ProductDTO getProductDetailsById(@PathVariable("id") long productId){
        if(productId==0){
            throw new IllegalArgumentException("No product with product Id 0");
        }
        else if(productId<0){
            throw new IllegalArgumentException("Please enter a positive product Id");
        }
        Product responseProduct = productService.getProductById(productId);
        if(responseProduct == null) {
            throw new RuntimeException("Product doesn't exist with id " + productId);
        }
        return from(responseProduct);
    }

    @PutMapping("/product/{id}")
    public ProductDTO putProductDetailsById(@PathVariable("id") long productId,@RequestBody ProductDTO productDTO){
        if(productId<=0){
            throw new IllegalArgumentException("You have entered an invalid product Id");
        }
        productDTO.setId(productId);
        Product product = from(productDTO);
        Product responseProduct = productService.replaceProductById(productId, product);
        if(responseProduct == null) {
            throw new RuntimeException("This action can not be completed for product with id " + productId);
        }

        return from(responseProduct);
    }

    @DeleteMapping("/product/{id}")
    public ProductDTO deleteProductById(@PathVariable("id") long productId){
        if(productId<=0){
            throw new IllegalArgumentException("You have entered an invalid product Id");
        }
        Product product = productService.deleteProductById(productId);
        return from(product);
    }

    @GetMapping("/product/all")
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productService.getAllProduct();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            productDTOS.add(from(product));
        }
        return productDTOS;
    }

    @PutMapping("/product/modify/{id}")
    public ProductDTO updateProductById(@PathVariable("id")  long productId,@RequestBody ProductDTO productDTO){
        return null;
    }

    @PostMapping("/product/{id}")
    public ProductDTO addProductById(@PathVariable("id") long productId,@RequestBody ProductDTO productDTO){
        if(productId<=0){
            throw new IllegalArgumentException("You have entered an invalid product Id");
        }
        Product product = from(productDTO);
        product.setId(productId);
        Product responseProduct = productService.addProduct(product);
        return from(responseProduct);
    }

    private ProductDTO from(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory());
        productDTO.setStatus(Status.SUCCESS);
        return productDTO;
    }

    private  Product from(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        return product;
    }
}
