package com.revature.cart.service;

import com.revature.cart.model.Cart;
import com.revature.cart.model.CartItem;
import com.revature.cart.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

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

    // Test for getUserInfo (Success)
    @Test
    public void testGetUserInfo_Success() {
        Long userId = 1L;
        String userInfo = "User Info";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(userInfo)).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = cartService.getUserInfo(userId);

        assertEquals(userInfo, result.block());
    }

    // Test for getUserInfo (Failure)
    @Test
    public void testGetUserInfo_Failure() {
        Long userId = 1L;

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = cartService.getUserInfo(userId);

        assertNull(result.block());
    }

    // Test for getProductInfo (Success)
    @Test
    public void testGetProductInfo_Success() {
        Long productId = 1L;
        String productInfo = "Product Info";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(productInfo)).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = cartService.getProductInfo(productId);

        assertEquals(productInfo, result.block());
    }

    // Test for getProductInfo (Failure)
    @Test
    public void testGetProductInfo_Failure() {
        Long productId = 1L;

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        Mono<String> result = cartService.getProductInfo(productId);

        assertNull(result.block());
    }

    // Test for addCart (Success)
    @Test
    public void testAddCart_Success() {
        Cart cart = new Cart();
        cart.setId(1L);

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.addCart(cart);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // Test for addCart (Failure)
    @Test
    public void testAddCart_Failure() {
        when(cartRepository.save(any(Cart.class))).thenReturn(null);

        Cart result = cartService.addCart(new Cart());

        assertNull(result);
    }

    // Test for getCartByUserId (Success)
    @Test
    public void testGetCartByUserId_Success() {
        Long userId = 1L;
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1L);
        carts.add(cart);

        when(cartRepository.findByUserId(userId)).thenReturn(carts);

        List<Cart> result = cartService.getCartByUserId(userId);

        assertFalse(result.isEmpty());
        assertEquals(1L, result.get(0).getId());
    }

    // Test for getCartByUserId (Failure)
    @Test
    public void testGetCartByUserId_Failure() {
        Long userId = 1L;

        when(cartRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        List<Cart> result = cartService.getCartByUserId(userId);

        assertTrue(result.isEmpty());
    }

    // Test for updateCart (Success)
    @Test
    public void testUpdateCart_Success() {
        Cart cart = new Cart();
        cart.setId(1L);

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.updateCart(cart);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // Test for updateCart (Failure)
    @Test
    public void testUpdateCart_Failure() {
        when(cartRepository.save(any(Cart.class))).thenReturn(null);

        Cart result = cartService.updateCart(new Cart());

        assertNull(result);
    }

    // Test for deleteCart (Success)
    @Test
    public void testDeleteCart_Success() {
        Long cartId = 1L;
        doNothing().when(cartRepository).deleteById(cartId);

        cartService.deleteCart(cartId);

        verify(cartRepository, times(1)).deleteById(cartId);
    }

    // Test for deleteCart (Failure)
    @Test
    public void testDeleteCart_Failure() {
        Long cartId = 1L;
        doThrow(new RuntimeException("Cart not found")).when(cartRepository).deleteById(cartId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartService.deleteCart(cartId);
        });

        assertEquals("Cart not found", exception.getMessage());
    }

    // Test for addItemToCart (Success)
    @Test
    public void testAddItemToCart_Success() {
        Long userId = 1L;
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1L);
        carts.add(cart);

        when(cartRepository.findByUserId(userId)).thenReturn(carts);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.addItemToCart(userId, cartItem);

        verify(cartRepository, times(1)).save(cart);
    }

    // Test for addItemToCart (Failure)
    @Test
    public void testAddItemToCart_Failure() {
        Long userId = 1L;
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);

        when(cartRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        cartService.addItemToCart(userId, cartItem);

        verify(cartRepository, times(1)).save(any(Cart.class));
    }
}
