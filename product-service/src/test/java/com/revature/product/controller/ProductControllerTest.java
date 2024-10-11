package com.revature.product.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.product.model.Product;
import com.revature.product.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    // Test for a successful scenario
    @Test
    public void testGetProductById_Success() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");

        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert that the response is 200 OK and the product is returned
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() != null);
        assert(response.getBody().getName().equals("Test Product"));
    }

    // Test for a failure scenario (product not found)
    @Test
    public void testGetProductById_Failure() {
        Long productId = 1L;

        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert that the response is 404 Not Found
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert(response.getBody() == null);
    }

    // Test for getting products by category ID (Success)
    @Test
    public void testGetProductsByCategoryId_Success() {
        Long categoryId = 1L;
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Category Test Product");
        productList.add(product);

        when(productService.getProductsByCategoryId(categoryId)).thenReturn(productList);

        ResponseEntity<List<Product>> response = productController.getProductsByCategoryId(categoryId);

        // Assert that the response is 200 OK and products are returned
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() != null);
        assert(!response.getBody().isEmpty());
    }

    // Test for getting products by category ID (Failure)
    @Test
    public void testGetProductsByCategoryId_Failure() {
        Long categoryId = 1L;

        when(productService.getProductsByCategoryId(categoryId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Product>> response = productController.getProductsByCategoryId(categoryId);

        // Assert that the response is 204 No Content
        assert(response.getStatusCode() == HttpStatus.NO_CONTENT);
        assert(response.getBody() == null);
    }
}

