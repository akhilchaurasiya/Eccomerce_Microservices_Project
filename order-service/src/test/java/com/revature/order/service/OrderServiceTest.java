package com.revature.order.service;

import com.revature.order.model.Order;
import com.revature.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    // Test for createOrder (Success)
    @Test
    public void testCreateOrder_Success() {
        Order order = new Order();
        order.setId(1L);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.createOrder(order);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // Test for createOrder (Failure)
    @Test
    public void testCreateOrder_Failure() {
        when(orderRepository.save(any(Order.class))).thenReturn(null);

        Order result = orderService.createOrder(new Order());

        assertNull(result);
    }

    // Test for getUserInfo (Success)
    @Test
    public void testGetUserInfo_Success() {
        String userId = "1";
        String userInfo = "User Info";

        // Using doReturn instead of thenReturn to avoid type mismatch issues
        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(userInfo)).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = orderService.getUserInfo(userId);

        assertEquals(userInfo, result.block());
    }

    // Test for getUserInfo (Failure)
    @Test
    public void testGetUserInfo_Failure() {
        String userId = "1";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = orderService.getUserInfo(userId);

        assertNull(result.block());
    }

    // Test for getProductInfo (Success)
    @Test
    public void testGetProductInfo_Success() {
        String productId = "1";
        String productInfo = "Product Info";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(productInfo)).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = orderService.getProductInfo(productId);

        assertEquals(productInfo, result.block());
    }

    // Test for getProductInfo (Failure)
    @Test
    public void testGetProductInfo_Failure() {
        String productId = "1";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = orderService.getProductInfo(productId);

        assertNull(result.block());
    }

    // Test for getCartInfo (Success)
    @Test
    public void testGetCartInfo_Success() {
        String cartId = "1";
        String cartInfo = "Cart Info";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(cartInfo)).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = orderService.getCartInfo(cartId);

        assertEquals(cartInfo, result.block());
    }

    // Test for getCartInfo (Failure)
    @Test
    public void testGetCartInfo_Failure() {
        String cartId = "1";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = orderService.getCartInfo(cartId);

        assertNull(result.block());
    }

    // Test for getOrderById (Success)
    @Test
    public void testGetOrderById_Success() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(orderId);

        assertTrue(result.isPresent());
        assertEquals(orderId, result.get().getId());
    }

    // Test for getOrderById (Failure)
    @Test
    public void testGetOrderById_Failure() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.getOrderById(orderId);

        assertFalse(result.isPresent());
    }
}
