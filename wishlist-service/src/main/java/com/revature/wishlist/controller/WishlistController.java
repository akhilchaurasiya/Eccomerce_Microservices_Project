package com.revature.wishlist.controller;

import com.revature.wishlist.model.Wishlist;
import com.revature.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.addWishlist(wishlist);
        return ResponseEntity.ok(createdWishlist);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Wishlist> getWishlistByUserId(@PathVariable Long userId) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistByUserId(userId);
        return wishlist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Wishlist> updateWishlist(@RequestBody Wishlist wishlist) {
        Wishlist updatedWishlist = wishlistService.updateWishlist(wishlist);
        return ResponseEntity.ok(updatedWishlist);
    }

    @DeleteMapping("/delete/{wishlistId}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addProduct/{userId}/{productId}")
    public ResponseEntity<Void> addProductToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-info/{userId}")
    public ResponseEntity<String> getUserInfo(@PathVariable Long userId) {
        return wishlistService.getUserInfo(userId)
                .map(ResponseEntity::ok)
                .block();
    }

    @GetMapping("/product-info/{productId}")
    public ResponseEntity<String> getProductInfo(@PathVariable Long productId) {
        return wishlistService.getProductInfo(productId)
                .map(ResponseEntity::ok)
                .block();
    }
}
