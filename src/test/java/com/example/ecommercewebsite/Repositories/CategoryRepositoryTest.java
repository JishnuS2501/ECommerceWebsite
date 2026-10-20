package com.example.ecommercewebsite.Repositories;

import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void fetchTypes(){
        Optional<Category> optionalCategory = categoryRepository.findById(1L);
        Category category = optionalCategory.get();
        for(Product product : category.getProducts()){
            System.out.println(product.getName());
        }

    }

}