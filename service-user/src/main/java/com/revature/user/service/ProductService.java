package com.revature.user.service;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.user.model.ProductRequest;
import com.revature.user.model.ProductResponse;

@Service
public class ProductService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Fetch all products
    public List<ProductResponse> getAllProducts() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/products") // API Gateway URL for Product Service
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block();
    }

    // Fetch a product by ID
    public ProductResponse getProductById(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/products/" + id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

    // Add a new product
    public String addProduct(ProductRequest productRequest) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8087/products") // API Gateway URL for adding a product
                .bodyValue(productRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Fetch all categories for adding a product
    public List<Category> getAllCategories() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/categories") // API Gateway URL for fetching categories
                .retrieve()
                .bodyToFlux(Category.class)
                .collectList()
                .block();
    }
}
