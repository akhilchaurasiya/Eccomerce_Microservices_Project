package com.revature.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;



@Controller
public class SellerController {

    // Mapping for Add Product
    @PostMapping("/postProduct")
    public ResponseEntity<String> postProduct(@RequestBody String jsonData) {
        // URL for product service
        String productUrl = "http://localhost:8082/products";

        // Sending the POST request with RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(productUrl, jsonData, String.class);

        return response;
    }

    @GetMapping("/addProduct")
    public String showAddProductPage() {
        return "addProduct"; // Corresponds to addProduct.jsp
    }

    // Mapping for All Products Page
    @GetMapping("/allProduct")
    public String showAllProductsPage(Model model) {
        // URL to fetch products from the product service
        String productUrl = "http://localhost:8082/products";

        // Use RestTemplate to fetch the product data from the external API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(productUrl, List.class);

        // Pass the products to the JSP view
        model.addAttribute("products", response.getBody());

        return "allProduct"; // Corresponds to allProduct.jsp
    }

    // Mapping for View Orders
    @GetMapping("/viewOrder")
    public String showViewOrdersPage() {
        return "viewOrder";  // Corresponds to viewOrders.jsp
    }

    @PostMapping("/postCategory")
    public ResponseEntity<String> postCategory(@RequestBody String jsonData) {
        // URL for category service
        String categoryUrl = "http://localhost:8082/categories";

        // Sending the POST request with RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(categoryUrl, jsonData, String.class);

        return response;
    }

    @GetMapping("/addCategory")
    public String showAddCategoryPage() {
        return "addCategory"; // Corresponds to addCategory.jsp
    }
    
 // Mapping for Edit Products
    @GetMapping("/editProduct")
    public String showEditProductsPage() {
        return "editProduct";  // Corresponds to editProducts.jsp
    }
    @GetMapping("/editProduct/{id}")
    public String showEditProductPage(@PathVariable Long id, Model model) {
        // URL to fetch a single product by its ID
        String productUrl = "http://localhost:8082/products/" + id;

        // Use RestTemplate to fetch the product by its ID
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(productUrl, Map.class);

        // Pass the product details to the editProduct.jsp view
        model.addAttribute("product", response.getBody());

        return "editProduct"; // Corresponds to editProduct.jsp
    }
    @PostMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody String jsonData) {
        String productUrl = "http://localhost:8082/products";

        // Send PUT request to update product
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(productUrl, HttpMethod.PUT, 
            new HttpEntity<>(jsonData, new HttpHeaders()), String.class);

        return response;
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        // The URL to delete the product in the product service
        String productUrl = "http://localhost:8082/products/" + id;

        // Create a RestTemplate instance to send the request
        RestTemplate restTemplate = new RestTemplate();

        // Send the DELETE request to the product service
        restTemplate.delete(productUrl);

        // Redirect back to the all products page after deletion
        return "redirect:/allProduct";
    }



    
}