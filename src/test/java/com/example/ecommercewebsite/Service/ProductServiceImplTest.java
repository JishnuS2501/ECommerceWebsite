package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Controller.ProductController;
import com.example.ecommercewebsite.DTO.ProductDTO;
import com.example.ecommercewebsite.Model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private IProductService iProductService;

    @Test
    public void testGetProductDetailsById_WithZero_resultsInIllegalArgException(){
        //Arrange
        long productId = 0L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProductDetailsById(productId));
        assertEquals("No product with product Id 0", exception.getMessage());
        verify(iProductService, times(0)).getProductById(productId);
    }

    @Test
    public void testGetProductDetailsById_WithNegativeId_resultsInIllegalArgException(){
        long productId = -5L;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProductDetailsById(productId));
        assertEquals("Please enter a positive product Id", exception.getMessage());
        verify(iProductService, times(0)).getProductById(productId);
    }

    @Test
    public void testGetProductDetailsById_WithValidProductId_returnsProductDTO(){
        long productId = 3L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Nothing 3A");
        product.setPrice(32000);
        when(iProductService.getProductById(productId)).thenReturn(product);

        ProductDTO responseProductDTO = productController.getProductDetailsById(productId);
        assertEquals(productId, responseProductDTO.getId());
        assertEquals(product.getPrice(), responseProductDTO.getPrice());
        assertEquals(product.getName(), responseProductDTO.getName());
        verify(iProductService, times(1)).getProductById(productId);
    }
}