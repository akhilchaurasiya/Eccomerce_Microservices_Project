package com.revature.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.user.model.Cart;
import com.revature.user.model.CartItem;

@Service
public class CartService {

    private final WebClient webClient;

    // Constructor with WebClient to call Cart API
    public CartService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8087/cart").build();  
    }

    // Add product to cart for a user (calls external API)
    public void addToCart(Long userId, CartItem cartItem) {
        Cart cart = new Cart(userId, List.of(cartItem));
        webClient.post()
                 .uri("/add")
                 .bodyValue(cart)
                 .retrieve()
                 .bodyToMono(Void.class)
                 .block();  // Blocking for synchronous response (for showcase)
    }

    // Fetch cart items for a user (calls external API)
    public Cart getCartByUserId(Long userId) {
        return webClient.get()
                        .uri("/user/{userId}", userId)
                        .retrieve()
                        .bodyToMono(Cart.class)
                        .block();
    }

    // Delete an item from cart by productId for a user
    public void removeCartItem(Long userId, Long productId) {
        webClient.delete()
                 .uri("/delete/{userId}/{productId}", userId, productId)
                 .retrieve()
                 .bodyToMono(Void.class)
                 .block();
    }
}
