package com.revature.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BuyerControllerTest {

    @InjectMocks
    private buyerController buyerController;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for showAllProductsPage (Success)
    @Test
    public void testShowAllProductsPage_Success() {
        // Prepare mock response
        List<String> mockProducts = Arrays.asList("Product1", "Product2");

        // Mock RestTemplate behavior
        String productUrl = "http://localhost:8087/products";
        when(restTemplate.getForEntity(productUrl, List.class))
                .thenReturn(new ResponseEntity<>(mockProducts, HttpStatus.OK));

        // Call the method
        String viewName = buyerController.showAllProductsPage(model);

        // Verify and assert
        verify(restTemplate, times(1)).getForEntity(productUrl, List.class);
        verify(model, times(1)).addAttribute("products", mockProducts);
        assertEquals("buyer/buyerPage", viewName);
    }

    // Test for showAllProductsPage (Failure)
    @Test
    public void testShowAllProductsPage_Failure() {
        // Mock RestTemplate behavior to simulate an error
        String productUrl = "http://localhost:8087/products";
        when(restTemplate.getForEntity(productUrl, List.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        // Call the method
        String viewName = buyerController.showAllProductsPage(model);

        // Verify and assert
        verify(restTemplate, times(1)).getForEntity(productUrl, List.class);
        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("buyer/buyerPage", viewName);  // Expecting the same JSP, but no products in the model
    }

    // Test for showAddToCartPage
    @Test
    public void testShowAddToCartPage() {
        // Call the method
        String viewName = buyerController.showAddToCartPage();

        // Verify and assert
        assertEquals("buyer/addToCart", viewName);
    }

    // Test for showCartPage
    @Test
    public void testShowCartPage() {
        // Call the method
        String viewName = buyerController.showCartPage();

        // Verify and assert
        assertEquals("buyer/cartPage", viewName);
    }

    // Test for viewProductsPage
    @Test
    public void testViewProductsPage() {
        // Call the method
        String viewName = buyerController.viewProductsPage();

        // Verify and assert
        assertEquals("buyer/buyerProduct", viewName);
    }
}
