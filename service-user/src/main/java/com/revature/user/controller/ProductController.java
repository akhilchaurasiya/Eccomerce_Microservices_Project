package com.revature.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.user.model.OrderResponse;
import com.revature.user.model.ProductResponse;
import com.revature.user.service.OrderService;
import com.revature.user.service.ProductService;

@Controller
public class ProductController {

    // Step 1: Create a Logger instance
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Step 2: Add logging and exception handling for home page
    @GetMapping("/home")
    public String showHomePage(Model model) {
        logger.info("Entering showHomePage()");
        
        try {
            List<ProductResponse> products = productService.getAllProducts();
            model.addAttribute("products", products);
            logger.info("Successfully fetched products for home page.");
        } catch (Exception e) {
            logger.error("Error fetching products for home page", e);
            throw new RuntimeException("Failed to load home page products.");
        }
        
        logger.info("Exiting showHomePage()");
        return "home";  // Refers to 'home.jsp'
    }

    // Step 3: Add logging and exception handling for all products
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        logger.info("Entering getAllProducts()");
        
        try {
            List<ProductResponse> products = productService.getAllProducts();
            model.addAttribute("products", products);
            logger.info("Successfully fetched all products.");
        } catch (Exception e) {
            logger.error("Error fetching all products", e);
            throw new RuntimeException("Failed to load products.");
        }
        
        logger.info("Exiting getAllProducts()");
        return "product";  // Refers to 'products.jsp'
    }



    // Step 5: Global exception handler for handling exceptions across all methods
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("An error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}