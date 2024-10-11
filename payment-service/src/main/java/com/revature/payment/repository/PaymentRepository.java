package com.revature.payment.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.payment.model.PaymentDetails;


public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {
    // Additional query methods can be added here if needed
}

