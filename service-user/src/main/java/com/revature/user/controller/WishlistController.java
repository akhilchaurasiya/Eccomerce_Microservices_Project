package com.revature.user.controller;import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.user.model.ProductResponse;
import com.revature.user.model.Wishlist;
import com.revature.user.service.ProductService;
import com.revature.user.service.WishlistService;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;
    
    @Autowired
    private ProductService productService; 
    
    @GetMapping
    public String showWishlistPage()
    {
    	return "buyer/wishlist";
    }
    
     

    @GetMapping("/user/{userId}")
    public String getWishlistsByUserId(@PathVariable Long userId, Model model) {
        // Fetch wishlists by userId
        List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(userId);

        // Create a list to store the actual products in the wishlist
        List<ProductResponse> wishlistProducts = new ArrayList<>();

        // For each productId in the wishlist, fetch the product details
        for (Wishlist wishlist : wishlists) {
            for (Long productId : wishlist.getProductIds()) {
                ProductResponse product = productService.getProductById(productId);  // Fetch product by ID
                if (product != null) {
                    wishlistProducts.add(product);  // Add product to the list
                }
            }
        }

        // Pass the products to the JSP
        model.addAttribute("wishlistProducts", wishlistProducts);
        model.addAttribute("userId", userId);
        return "wishlist";  // Return to wishlist.jsp
    }

    @PostMapping("/add")
    public String addWishlist(@ModelAttribute Wishlist wishlist) {
        wishlistService.addWishlist(wishlist);
        return "redirect:/wishlist/user/" + wishlist.getUserId();
    }

    @PostMapping("/update")
    public String updateWishlist(@ModelAttribute Wishlist wishlist) {
        wishlistService.updateWishlist(wishlist);
        return "redirect:/wishlist/user/" + wishlist.getUserId();
    }

    @GetMapping("/delete/{wishlistId}")
    public String deleteWishlist(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
        return "redirect:/wishlist";
    }

    
    @PostMapping("/addProduct/{productId}")
    public String addProductToWishlist(@PathVariable Long productId) {
        // Hardcoded userId = 1
        Long userId = 1L;

        // Create a new wishlist with the hardcoded userId and the productId
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductIds(Arrays.asList(productId));

        // Call the service to add the wishlist
        wishlistService.addWishlist(wishlist);

        return "redirect:/wishlist";
    }

    

    @GetMapping("/removeProduct/{userId}/{productId}")
    public String removeProductFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
        return "redirect:/wishlist/user/" + userId;
    }
}