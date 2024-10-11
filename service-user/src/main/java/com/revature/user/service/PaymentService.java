package com.revature.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.user.model.PaymentDetails;

@Service
public class PaymentService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String processPayment(PaymentDetails paymentDetails) {
        // Use WebClient to send a request to the payment backend
        String response = webClientBuilder.build()
                .post()
                .uri("http://payment-service/payment/process")  // URL through API Gateway
                .bodyValue(paymentDetails)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // Blocking for simplicity; can be improved with reactive approach

        return response;  // Return response from backend (e.g., "Payment Successful")
    }
}


