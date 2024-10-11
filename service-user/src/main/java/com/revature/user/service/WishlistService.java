package com.revature.user.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.user.model.Wishlist;

import reactor.core.publisher.Mono;

@Service
public class WishlistService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String BASE_URL = "http://localhost:8087/wishlist";

    public List<Wishlist> getWishlistsByUserId(Long userId) {
        return Arrays.asList(webClientBuilder.build()
                .get()
                .uri(BASE_URL + "/user/" + userId)
                .retrieve()
                .bodyToMono(Wishlist[].class)
                .block());
    }

//    public Wishlist addWishlist(Wishlist wishlist) {
//        return webClientBuilder.build()
//                .post()
//                .uri(BASE_URL + "/add")
//                .body(Mono.just(wishlist), Wishlist.class)
//                .retrieve()
//                .bodyToMono(Wishlist.class)
//                .block();
//    }
    
    public Wishlist addWishlist(Wishlist wishlist) {
        return webClientBuilder.build()
                .post()
                .uri(BASE_URL + "/add")
                .body(Mono.just(wishlist), Wishlist.class)  // Send the Wishlist object as the request body
                .retrieve()
                .bodyToMono(Wishlist.class)
                .block();  // Block to make the call synchronous
    }


    public Wishlist updateWishlist(Wishlist wishlist) {
        return webClientBuilder.build()
                .put()
                .uri(BASE_URL + "/update")
                .body(Mono.just(wishlist), Wishlist.class)
                .retrieve()
                .bodyToMono(Wishlist.class)
                .block();
    }

    public void deleteWishlist(Long wishlistId) {
        webClientBuilder.build()
                .delete()
                .uri(BASE_URL + "/delete/" + wishlistId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

//    public void addProductToWishlist(Long userId, Long productId) {
//        webClientBuilder.build()
//                .post()
//                .uri(BASE_URL + "/addProduct/" + userId + "/" + productId)
//                .retrieve()
//                .bodyToMono(Void.class)
//                .block();
//    }
    
    public void addProductToWishlist(Long productId) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(1L);  // Hardcoded userId
        wishlist.setProductIds(Arrays.asList(productId)); // Adding productId to wishlist

        webClientBuilder.build()
                .post()
                .uri(BASE_URL + "/add")
                .body(Mono.just(wishlist), Wishlist.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();  // Block until request completes
    }

    public void removeProductFromWishlist(Long userId, Long productId) {
        webClientBuilder.build()
                .delete()
                .uri(BASE_URL + "/removeProduct/" + userId + "/" + productId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}