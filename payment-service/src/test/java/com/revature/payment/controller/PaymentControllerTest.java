package com.revature.payment.controller;

import com.revature.payment.model.PaymentDetails;
import com.revature.payment.model.PaymentStatus;
import com.revature.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    // Test for createPayment (Success)
    @Test
    public void testCreatePayment_Success() {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(1L);

        when(paymentService.createPayment(any(PaymentDetails.class))).thenReturn(paymentDetails);

        ResponseEntity<PaymentDetails> response = paymentController.createPayment(paymentDetails);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    // Test for createPayment (Failure)
    @Test
    public void testCreatePayment_Failure() {
        when(paymentService.createPayment(any(PaymentDetails.class))).thenReturn(null);

        ResponseEntity<PaymentDetails> response = paymentController.createPayment(new PaymentDetails());

        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Still returns CREATED status but no body
        assertNull(response.getBody());
    }

    // Test for updatePaymentStatus (Success)
    @Test
    public void testUpdatePaymentStatus_Success() {
        Long paymentId = 1L;
        PaymentStatus status = PaymentStatus.SUCCESS;
        PaymentDetails updatedPaymentDetails = new PaymentDetails();
        updatedPaymentDetails.setId(paymentId);
        updatedPaymentDetails.setStatus(status);

        when(paymentService.updatePaymentStatus(paymentId, status)).thenReturn(updatedPaymentDetails);

        ResponseEntity<PaymentDetails> response = paymentController.updatePaymentStatus(paymentId, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(status, response.getBody().getStatus());
    }

    // Test for updatePaymentStatus (Failure)
    @Test
    public void testUpdatePaymentStatus_Failure() {
        Long paymentId = 1L;
        PaymentStatus status = PaymentStatus.SUCCESS;

        when(paymentService.updatePaymentStatus(paymentId, status)).thenReturn(null);

        ResponseEntity<PaymentDetails> response = paymentController.updatePaymentStatus(paymentId, status);

        assertNull(response.getBody());
    }

    // Test for getPaymentDetails (Success)
    @Test
    public void testGetPaymentDetails_Success() {
        Long paymentId = 1L;
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(paymentId);

        when(paymentService.getPaymentDetails(paymentId)).thenReturn(Optional.of(paymentDetails));

        ResponseEntity<PaymentDetails> response = paymentController.getPaymentDetails(paymentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(paymentId, response.getBody().getId());
    }

    // Test for getPaymentDetails (Failure)
    @Test
    public void testGetPaymentDetails_Failure() {
        Long paymentId = 1L;

        when(paymentService.getPaymentDetails(paymentId)).thenReturn(Optional.empty());

        ResponseEntity<PaymentDetails> response = paymentController.getPaymentDetails(paymentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test for getUserInfo (Success)
    @Test
    public void testGetUserInfo_Success() {
        String userId = "1";
        String userInfo = "User Info";

        when(paymentService.getUserInfo(userId)).thenReturn(userInfo);

        ResponseEntity<String> response = paymentController.getUserInfo(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userInfo, response.getBody());
    }

    // Test for getUserInfo (Failure)
    @Test
    public void testGetUserInfo_Failure() {
        String userId = "1";

        when(paymentService.getUserInfo(userId)).thenReturn(null);

        ResponseEntity<String> response = paymentController.getUserInfo(userId);

        assertNull(response.getBody());
    }
}
