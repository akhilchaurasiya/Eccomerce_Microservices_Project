package com.revature.payment.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.payment.model.PaymentDetails;
import com.revature.payment.model.PaymentStatus;
import com.revature.payment.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WebClient.Builder webClientBuilder; // WebClient for external service interaction

    private final String userServiceUrl = "http://service-user/users";
    private final String productServiceUrl = "http://product-service/products";
    private final String cartServiceUrl = "http://cart-service/cart";
    private final String orderServiceUrl = "http://order-service/orders";

    public PaymentDetails createPayment(PaymentDetails paymentDetails) {
        // Additional validation and logic can go here

        // Example: Call external services to validate the order
        String orderInfo = webClientBuilder.build()
                .get()
                .uri(orderServiceUrl + "/" + paymentDetails.getOrderId())
                .retrieve()
                .bodyToMono(String.class) // Avoid DTO, get raw response
                .block(); // Blocking call for simplicity

        // Save payment details after fetching external service data
        paymentDetails.setStatus(PaymentStatus.INITIATED);
        return paymentRepository.save(paymentDetails);
    }

    public PaymentDetails updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Optional<PaymentDetails> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isPresent()) {
            PaymentDetails payment = paymentOpt.get();
            payment.setStatus(status);
            return paymentRepository.save(payment);
        } else {
            throw new IllegalArgumentException("Payment not found");
        }
    }

    public Optional<PaymentDetails> getPaymentDetails(Long id) {
        return paymentRepository.findById(id);
    }

    // Example method to interact with User Service
    public String getUserInfo(String userId) {
        return webClientBuilder.build()
                .get()
                .uri(userServiceUrl + "/" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Avoiding DTOs and getting raw JSON/String
    }
}

