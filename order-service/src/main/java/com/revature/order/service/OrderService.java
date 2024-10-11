package com.revature.order.service;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.order.model.Order;
import com.revature.order.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public OrderService(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    // Create a new order
    public Order createOrder(Order order) {
        // Save order to the database
        return orderRepository.save(order);
    }

    // Get user info from the UserService
    public Mono<String> getUserInfo(String userId) {
        return webClientBuilder.build()
            .get()
            .uri("http://user-service/users/" + userId) // User service
            .retrieve()
            .bodyToMono(String.class);
    }

    // Get product info from ProductService
    public Mono<String> getProductInfo(String productId) {
        return webClientBuilder.build()
            .get()
            .uri("http://product-service/products/" + productId) // Product service
            .retrieve()
            .bodyToMono(String.class);
    }

    // Get cart info from CartService
    public Mono<String> getCartInfo(String cartId) {
        return webClientBuilder.build()
            .get()
            .uri("http://cart-service/cart/" + cartId) // Cart service
            .retrieve()
            .bodyToMono(String.class);
    }

    // Find order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
