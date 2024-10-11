package com.revature.wishlist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.wishlist.model.Wishlist;
import com.revature.wishlist.repository.WishlistRepository;

import reactor.core.publisher.Mono;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Wishlist addWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist updateWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public void addProductToWishlist(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user: " + userId));

        // Check if the product is already in the wishlist
        if (wishlist.getProductIds() != null && wishlist.getProductIds().contains(productId)) {
            throw new RuntimeException("Product already exists in the wishlist");
        }

        // Add product to the wishlist
        wishlist.getProductIds().add(productId);
        wishlistRepository.save(wishlist);
    }

    public Mono<String> getUserInfo(Long userId) {
        return webClientBuilder.build()
            .get()
            .uri("http://localhost:8081/users/" + userId)
            .retrieve()
            .bodyToMono(String.class);
    }

    public Mono<String> getProductInfo(Long productId) {
        return webClientBuilder.build()
            .get()
            .uri("http://localhost:8082/products/" + productId)
            .retrieve()
            .bodyToMono(String.class);
    }
}
