package com.revature.cart.controller;

import com.revature.cart.model.Cart;
import com.revature.cart.model.CartItem;
import com.revature.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    // Test for adding a cart (Success)
    @Test
    public void testAddCart_Success() {
        Cart cart = new Cart();
        cart.setId(1L);

        when(cartService.addCart(any(Cart.class))).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.addCart(cart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    // Test for adding a cart (Failure)
    @Test
    public void testAddCart_Failure() {
        when(cartService.addCart(any(Cart.class))).thenReturn(null);

        ResponseEntity<Cart> response = cartController.addCart(new Cart());

        assertNull(response.getBody());
    }

    // Test for getting cart by user ID (Success)
    @Test
    public void testGetCartByUserId_Success() {
        Long userId = 1L;
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1L);
        carts.add(cart);

        when(cartService.getCartByUserId(userId)).thenReturn(carts);

        ResponseEntity<List<Cart>> response = cartController.getCartByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
    }

    // Test for getting cart by user ID (Failure)
    @Test
    public void testGetCartByUserId_Failure() {
        Long userId = 1L;

        when(cartService.getCartByUserId(userId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Cart>> response = cartController.getCartByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test for updating a cart (Success)
    @Test
    public void testUpdateCart_Success() {
        Cart cart = new Cart();
        cart.setId(1L);

        when(cartService.updateCart(any(Cart.class))).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.updateCart(cart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    // Test for updating a cart (Failure)
    @Test
    public void testUpdateCart_Failure() {
        when(cartService.updateCart(any(Cart.class))).thenReturn(null);

        ResponseEntity<Cart> response = cartController.updateCart(new Cart());

        assertNull(response.getBody());
    }

    // Test for deleting a cart (Success)
    @Test
    public void testDeleteCart_Success() {
        Long cartId = 1L;
        doNothing().when(cartService).deleteCart(cartId);

        ResponseEntity<Void> response = cartController.deleteCart(cartId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cartService, times(1)).deleteCart(cartId);
    }

    // Test for deleting a cart (Failure)
    @Test
    public void testDeleteCart_Failure() {
        Long cartId = 1L;
        doThrow(new RuntimeException("Cart not found")).when(cartService).deleteCart(cartId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartController.deleteCart(cartId);
        });

        assertEquals("Cart not found", exception.getMessage());
    }

    // Test for adding an item to the cart (Success)
    @Test
    public void testAddItemToCart_Success() {
        Long userId = 1L;
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);

        doNothing().when(cartService).addItemToCart(userId, cartItem);

        ResponseEntity<Void> response = cartController.addItemToCart(userId, cartItem);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cartService, times(1)).addItemToCart(userId, cartItem);
    }

    // Test for adding an item to the cart (Failure)
    @Test
    public void testAddItemToCart_Failure() {
        Long userId = 1L;
        CartItem cartItem = new CartItem();

        doThrow(new RuntimeException("User not found")).when(cartService).addItemToCart(userId, cartItem);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartController.addItemToCart(userId, cartItem);
        });

        assertEquals("User not found", exception.getMessage());
    }

    // Test for fetching user info (Success)
    @Test
    public void testGetUserInfo_Success() {
        Long userId = 1L;
        String userInfo = "User Info";

        when(cartService.getUserInfo(userId)).thenReturn(Mono.just(userInfo));

        ResponseEntity<String> response = cartController.getUserInfo(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userInfo, response.getBody());
    }

    // Test for fetching user info (Failure)
    @Test
    public void testGetUserInfo_Failure() {
        Long userId = 1L;

        when(cartService.getUserInfo(userId)).thenReturn(Mono.empty());

        ResponseEntity<String> response = cartController.getUserInfo(userId);

        assertNull(response);
    }

    // Test for fetching product info (Success)
    @Test
    public void testGetProductInfo_Success() {
        Long productId = 1L;
        String productInfo = "Product Info";

        when(cartService.getProductInfo(productId)).thenReturn(Mono.just(productInfo));

        ResponseEntity<String> response = cartController.getProductInfo(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productInfo, response.getBody());
    }

    // Test for fetching product info (Failure)
    @Test
    public void testGetProductInfo_Failure() {
        Long productId = 1L;

        when(cartService.getProductInfo(productId)).thenReturn(Mono.empty());

        ResponseEntity<String> response = cartController.getProductInfo(productId);

        assertNull(response);
    }
}
