package com.revature.user.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;


import com.revature.user.model.Order;
import com.revature.user.service.OrderService;


@Controller
public class buyerController {
	
	private static final Logger logger = LoggerFactory.getLogger(buyerController.class);
	@Autowired
    private final OrderService orderService;

    @Autowired
    public buyerController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Fetch an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        logger.info("Fetching order by ID: {}", id);
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        logger.info("Creating a new order");
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Update order status by ID
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        logger.info("Updating order status for ID: {}", id);
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
	
	 @GetMapping("/buyerPage")
	    public String showAllProductsPage(Model model) {
	        // URL to fetch products from the product service
	        String productUrl = "http://localhost:8082/products";

	        // Use RestTemplate to fetch the product data from the external API
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<List> response = restTemplate.getForEntity(productUrl, List.class);

	        // Pass the products to the JSP view
	        model.addAttribute("products", response.getBody());

	        return "buyer/buyerPage"; // Corresponds to allProduct.jsp
	    }
	 


	 @GetMapping("/addToCart")
	    public String showAddToCartPage() {
	        return "buyer/addToCart";  // Corresponds to the new addToCart.jsp
	    }
	 
	 @GetMapping("/cartPage")
	    public String showCartPage() {
	        return "buyer/cartPage";  // This corresponds to cartPage.jsp
	    }

	 
	 
	 @GetMapping("/buyerAllProduct")
	    public String viewProductsPage() {
	        return "buyer/buyerProduct";  // This returns the JSP page
	    }
	 

	 
	 @GetMapping("/processPayment")
		public String viewPaymentPage(@RequestParam("totalPrice") double totalPrice, Model model) {
		    model.addAttribute("totalPrice", totalPrice);
		    return "buyer/paymentForm";  // Return the JSP page
		}

		
		
		@PostMapping("/successPayment")
	    public String successPayment() {
	        return "buyer/successPayment";  // This returns the JSP page
	    }
		
		 @GetMapping("/orders")
		    public String viewOrders() {
		        return "buyer/orders";  // The JSP page for orders should be in /WEB-INF/views/buyer/orders.jsp
		    }
}
