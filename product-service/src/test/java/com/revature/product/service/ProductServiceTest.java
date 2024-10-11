package com.revature.product.service;

import com.revature.product.model.Product;
import com.revature.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    // Test for creating a product (Success)
    @Test
    public void testCreateProduct_Success() {
        Product product = new Product();
        product.setName("New Product");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("New Product", createdProduct.getName());
    }

    // Test for getting a product by ID (Success)
    @Test
    public void testGetProductById_Success() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> retrievedProduct = productService.getProductById(productId);

        assertTrue(retrievedProduct.isPresent());
        assertEquals("Test Product", retrievedProduct.get().getName());
    }

    // Test for getting a product by ID (Failure)
    @Test
    public void testGetProductById_Failure() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> retrievedProduct = productService.getProductById(productId);

        assertFalse(retrievedProduct.isPresent());
    }

    // Test for updating a product (Success)
    @Test
    public void testUpdateProduct_Success() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Old Product");

        Product updatedProductDetails = new Product();
        updatedProductDetails.setName("Updated Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProductDetails);

        Product updatedProduct = productService.updateProduct(productId, updatedProductDetails);

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
    }

    // Test for updating a product (Failure)
    @Test
    public void testUpdateProduct_Failure() {
        Long productId = 1L;
        Product updatedProductDetails = new Product();
        updatedProductDetails.setName("Updated Product");

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(productId, updatedProductDetails);
        });

        assertEquals("Product not found with id 1", exception.getMessage());
    }

    // Test for deleting a product (Success)
    @Test
    public void testDeleteProduct_Success() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).delete(product);
    }

    // Test for deleting a product (Failure)
    @Test
    public void testDeleteProduct_Failure() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct(productId);
        });

        assertEquals("Product not found with id 1", exception.getMessage());
    }

    // Test for getting products by category ID (Success)
    @Test
    public void testGetProductsByCategoryId_Success() {
        Long categoryId = 1L;
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Category Test Product");
        productList.add(product);

        when(productRepository.findByCategoryId(categoryId)).thenReturn(productList);

        List<Product> retrievedProducts = productService.getProductsByCategoryId(categoryId);

        assertFalse(retrievedProducts.isEmpty());
        assertEquals("Category Test Product", retrievedProducts.get(0).getName());
    }

    // Test for getting products by category ID (Failure)
    @Test
    public void testGetProductsByCategoryId_Failure() {
        Long categoryId = 1L;

        when(productRepository.findByCategoryId(categoryId)).thenReturn(new ArrayList<>());

        List<Product> retrievedProducts = productService.getProductsByCategoryId(categoryId);

        assertTrue(retrievedProducts.isEmpty());
    }
}
