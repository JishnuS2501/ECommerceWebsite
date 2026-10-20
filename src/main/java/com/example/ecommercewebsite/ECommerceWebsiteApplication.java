package com.example.ecommercewebsite;

import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.State;
import com.example.ecommercewebsite.Repositories.CategoryRepository;
import com.example.ecommercewebsite.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ECommerceWebsiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(ECommerceWebsiteApplication.class, args);
	}
}
