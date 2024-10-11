package com.revature.user.controller;

import com.revature.user.model.ProductResponse;
import com.revature.user.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for showHomePage (Success)
    @Test
    public void testShowHomePage_Success() {
        // Prepare mock data
        List<ProductResponse> mockProducts = Arrays.asList(
                new ProductResponse(),
                new ProductResponse()
        );

        // Mock productService behavior
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Call the method
        String viewName = productController.showHomePage(model);

        // Verify and assert
        verify(productService, times(1)).getAllProducts();
        verify(model, times(1)).addAttribute("products", mockProducts);
        assertEquals("home", viewName);
    }

    // Test for showHomePage (Failure)
    @Test
    public void testShowHomePage_Failure() {
        // Mock productService to throw an exception
        when(productService.getAllProducts()).thenThrow(new RuntimeException("Failed to fetch products"));

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productController.showHomePage(model);
        });

        // Assert exception message
        assertEquals("Failed to load home page products.", exception.getMessage());
    }

    // Test for getAllProducts (Success)
    @Test
    public void testGetAllProducts_Success() {
        // Prepare mock data
        List<ProductResponse> mockProducts = Arrays.asList(
                new ProductResponse(),
                new ProductResponse()
        );

        // Mock productService behavior
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Call the method
        String viewName = productController.getAllProducts(model);

        // Verify and assert
        verify(productService, times(1)).getAllProducts();
        verify(model, times(1)).addAttribute("products", mockProducts);
        assertEquals("product", viewName);
    }

    // Test for getAllProducts (Failure)
    @Test
    public void testGetAllProducts_Failure() {
        // Mock productService to throw an exception
        when(productService.getAllProducts()).thenThrow(new RuntimeException("Failed to fetch products"));

        // Call the method and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productController.getAllProducts(model);
        });

        // Assert exception message
        assertEquals("Failed to load products.", exception.getMessage());
    }
}
