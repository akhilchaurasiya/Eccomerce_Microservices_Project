package com.revature.order.controller;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.order.model.Order;
import com.revature.order.service.OrderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Get user info from UserService
    @GetMapping("/user/{userId}")
    public Mono<String> getUserInfo(@PathVariable String userId) {
        return orderService.getUserInfo(userId);
    }

    // Get product info from ProductService
    @GetMapping("/product/{productId}")
    public Mono<String> getProductInfo(@PathVariable String productId) {
        return orderService.getProductInfo(productId);
    }

    // Get cart info from CartService
    @GetMapping("/cart/{cartId}")
    public Mono<String> getCartInfo(@PathVariable String cartId) {
        return orderService.getCartInfo(cartId);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
