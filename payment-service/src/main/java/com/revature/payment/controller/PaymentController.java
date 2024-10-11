package com.revature.payment.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.payment.model.PaymentDetails;
import com.revature.payment.model.PaymentStatus;
import com.revature.payment.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDetails> createPayment(@RequestBody PaymentDetails paymentDetails) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDetails), HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}/status")
    public ResponseEntity<PaymentDetails> updatePaymentStatus(@PathVariable Long paymentId, @RequestParam PaymentStatus status) {
        return new ResponseEntity<>(paymentService.updatePaymentStatus(paymentId, status), HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDetails> getPaymentDetails(@PathVariable Long paymentId) {
        Optional<PaymentDetails> paymentDetails = paymentService.getPaymentDetails(paymentId);
        return paymentDetails.map(payment -> new ResponseEntity<>(payment, HttpStatus.OK))
                             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Example endpoint to demonstrate external interaction (e.g., User Info)
    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getUserInfo(@PathVariable String userId) {
        String userInfo = paymentService.getUserInfo(userId);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}

