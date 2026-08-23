package com.example.ecommercewebsite.Repositories;

import com.example.ecommercewebsite.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
