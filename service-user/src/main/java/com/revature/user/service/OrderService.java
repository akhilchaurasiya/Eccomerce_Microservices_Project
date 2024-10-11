package com.revature.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.user.model.Order;
import com.revature.user.model.OrderResponse;


@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final WebClient webClient;



    @Autowired
    public OrderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                            .baseUrl("http://localhost:8087")
                            .build();
    }

    public Order getOrderById(Long id) {
        logger.info("Fetching order with ID: {}", id);

        try {
            Order order = webClient.get()
                                   .uri("/orders/{id}", id)
                                   .retrieve()
                                   .bodyToMono(Order.class)
                                   .block();  // Blocking for synchronous response
            logger.info("Order fetched successfully with ID: {}", id);
            return order;
        } catch (Exception e) {
            logger.error("Error while fetching order with ID: {}", id, e);
            throw new RuntimeException("Failed to fetch order.");
        }
    }

    public Order createOrder(Order order) {
        logger.info("Creating new order: {}", order);

        try {
            Order createdOrder = webClient.post()
                                          .uri("/orders")
                                          .bodyValue(order)
                                          .retrieve()
                                          .bodyToMono(Order.class)
                                          .block();
            logger.info("Order created successfully with ID: {}", createdOrder.getId());
            return createdOrder;
        } catch (Exception e) {
            logger.error("Error while creating order: {}", order, e);
            throw new RuntimeException("Failed to create order.");
        }
    }

    public Order updateOrderStatus(Long id, String status) {
        logger.info("Updating order status for order ID: {}", id);

        try {
            Order updatedOrder = webClient.put()
                                          .uri("/orders/{id}/status", id)
                                          .bodyValue(status)
                                          .retrieve()
                                          .bodyToMono(Order.class)
                                          .block();
            logger.info("Order status updated successfully for order ID: {}", id);
            return updatedOrder;
        } catch (Exception e) {
            logger.error("Error while updating order status for order ID: {}", id, e);
            throw new RuntimeException("Failed to update order status.");
        }
    }


}