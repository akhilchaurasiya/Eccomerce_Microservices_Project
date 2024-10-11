package com.revature.order.controller;

import com.revature.order.model.Order;
import com.revature.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    // Test for createOrder (Success)
    @Test
    public void testCreateOrder_Success() {
        Order order = new Order();
        order.setId(1L);

        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        ResponseEntity<Order> response = orderController.createOrder(order);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    // Test for createOrder (Failure)
    @Test
    public void testCreateOrder_Failure() {
        when(orderService.createOrder(any(Order.class))).thenReturn(null);

        ResponseEntity<Order> response = orderController.createOrder(new Order());

        assertNull(response.getBody());
    }

    // Test for getUserInfo (Success)
    @Test
    public void testGetUserInfo_Success() {
        String userId = "1";
        String userInfo = "User Info";

        when(orderService.getUserInfo(userId)).thenReturn(Mono.just(userInfo));

        Mono<String> result = orderController.getUserInfo(userId);

        assertEquals(userInfo, result.block());
    }

    // Test for getUserInfo (Failure)
    @Test
    public void testGetUserInfo_Failure() {
        String userId = "1";

        when(orderService.getUserInfo(userId)).thenReturn(Mono.empty());

        Mono<String> result = orderController.getUserInfo(userId);

        assertNull(result.block());
    }

    // Test for getProductInfo (Success)
    @Test
    public void testGetProductInfo_Success() {
        String productId = "1";
        String productInfo = "Product Info";

        when(orderService.getProductInfo(productId)).thenReturn(Mono.just(productInfo));

        Mono<String> result = orderController.getProductInfo(productId);

        assertEquals(productInfo, result.block());
    }

    // Test for getProductInfo (Failure)
    @Test
    public void testGetProductInfo_Failure() {
        String productId = "1";

        when(orderService.getProductInfo(productId)).thenReturn(Mono.empty());

        Mono<String> result = orderController.getProductInfo(productId);

        assertNull(result.block());
    }

    // Test for getCartInfo (Success)
    @Test
    public void testGetCartInfo_Success() {
        String cartId = "1";
        String cartInfo = "Cart Info";

        when(orderService.getCartInfo(cartId)).thenReturn(Mono.just(cartInfo));

        Mono<String> result = orderController.getCartInfo(cartId);

        assertEquals(cartInfo, result.block());
    }

    // Test for getCartInfo (Failure)
    @Test
    public void testGetCartInfo_Failure() {
        String cartId = "1";

        when(orderService.getCartInfo(cartId)).thenReturn(Mono.empty());

        Mono<String> result = orderController.getCartInfo(cartId);

        assertNull(result.block());
    }

    // Test for getOrderById (Success)
    @Test
    public void testGetOrderById_Success() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<Order> response = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().getId());
    }

    // Test for getOrderById (Failure)
    @Test
    public void testGetOrderById_Failure() {
        Long orderId = 1L;

        when(orderService.getOrderById(orderId)).thenReturn(Optional.empty());

        ResponseEntity<Order> response = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
