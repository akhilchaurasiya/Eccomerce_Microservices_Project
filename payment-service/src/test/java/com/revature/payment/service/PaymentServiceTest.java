package com.revature.payment.service;

import com.revature.payment.model.PaymentDetails;
import com.revature.payment.model.PaymentStatus;
import com.revature.payment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    // Test for createPayment (Success)
    @Test
    public void testCreatePayment_Success() {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderId("1");
        paymentDetails.setStatus(PaymentStatus.INITIATED);

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just("Order Info")).when(responseSpec).bodyToMono(String.class);

        when(paymentRepository.save(any(PaymentDetails.class))).thenReturn(paymentDetails);

        PaymentDetails result = paymentService.createPayment(paymentDetails);

        assertNotNull(result);
        assertEquals(PaymentStatus.INITIATED, result.getStatus());
        verify(paymentRepository, times(1)).save(paymentDetails);
    }

    // Test for createPayment (Failure)
    @Test
    public void testCreatePayment_Failure() {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderId("1");

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        PaymentDetails result = paymentService.createPayment(paymentDetails);

        assertNull(result);  // You can adjust this depending on how you want to handle the failure
    }

    // Test for updatePaymentStatus (Success)
    @Test
    public void testUpdatePaymentStatus_Success() {
        Long paymentId = 1L;
        PaymentStatus status = PaymentStatus.SUCCESS;
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(paymentId);
        paymentDetails.setStatus(PaymentStatus.INITIATED);

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(paymentDetails));
        when(paymentRepository.save(any(PaymentDetails.class))).thenReturn(paymentDetails);

        PaymentDetails result = paymentService.updatePaymentStatus(paymentId, status);

        assertNotNull(result);
        assertEquals(PaymentStatus.SUCCESS, result.getStatus());
        verify(paymentRepository, times(1)).save(paymentDetails);
    }

    // Test for updatePaymentStatus (Failure)
    @Test
    public void testUpdatePaymentStatus_Failure() {
        Long paymentId = 1L;
        PaymentStatus status = PaymentStatus.SUCCESS;

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.updatePaymentStatus(paymentId, status);
        });

        verify(paymentRepository, times(0)).save(any(PaymentDetails.class));
    }

    // Test for getPaymentDetails (Success)
    @Test
    public void testGetPaymentDetails_Success() {
        Long paymentId = 1L;
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(paymentId);

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(paymentDetails));

        Optional<PaymentDetails> result = paymentService.getPaymentDetails(paymentId);

        assertTrue(result.isPresent());
        assertEquals(paymentId, result.get().getId());
    }

    // Test for getPaymentDetails (Failure)
    @Test
    public void testGetPaymentDetails_Failure() {
        Long paymentId = 1L;

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        Optional<PaymentDetails> result = paymentService.getPaymentDetails(paymentId);

        assertFalse(result.isPresent());
    }

    // Test for getUserInfo (Success)
    @Test
    public void testGetUserInfo_Success() {
        String userId = "1";
        String userInfo = "User Info";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(userInfo)).when(responseSpec).bodyToMono(String.class);

        String result = paymentService.getUserInfo(userId);

        assertNotNull(result);
        assertEquals(userInfo, result);
    }

    // Test for getUserInfo (Failure)
    @Test
    public void testGetUserInfo_Failure() {
        String userId = "1";

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.empty()).when(responseSpec).bodyToMono(String.class);

        String result = paymentService.getUserInfo(userId);

        assertNull(result);
    }
}
