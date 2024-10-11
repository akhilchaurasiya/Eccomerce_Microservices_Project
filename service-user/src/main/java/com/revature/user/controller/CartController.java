package com.revature.user.controller;

import com.revature.user.model.Cart;
import com.revature.user.model.CartItem;
import com.revature.user.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add to cart endpoint
    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody Cart cart) {
        if (cart != null && cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
            CartItem cartItem = cart.getCartItems().get(0);  // Assuming adding a single item at a time
            cartService.addToCart(cart.getUserId(), cartItem);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get cart by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Remove an item from the cart
    @DeleteMapping("/delete/{userId}/{productId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeCartItem(userId, productId);
        return ResponseEntity.ok().build();
    }
}
