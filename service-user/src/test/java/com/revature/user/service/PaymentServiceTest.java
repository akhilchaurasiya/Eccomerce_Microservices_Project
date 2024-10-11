package com.revature.user.service;

import com.revature.user.model.PaymentDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    public void setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // You can remove the unnecessary stubbing here or make it lenient.
        // doReturn(webClientBuilder).when(webClientBuilder).baseUrl(anyString());
        doReturn(webClient).when(webClientBuilder).build();
    }

    // Test for processPayment (Success)
    @Test
    public void testProcessPayment_Success() {
        PaymentDetails mockPaymentDetails = new PaymentDetails();
        mockPaymentDetails.setAmount("100.00");

        String mockResponse = "Payment Successful";

        // Mocking WebClient behavior using doReturn
        doReturn(requestBodyUriSpec).when(webClient).post();
        doReturn(requestBodySpec).when(requestBodyUriSpec).uri("http://payment-service/payment/process");
        doReturn(requestHeadersSpec).when(requestBodySpec).bodyValue(mockPaymentDetails);
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(mockResponse)).when(responseSpec).bodyToMono(String.class);

        // Call the method
        String response = paymentService.processPayment(mockPaymentDetails);

        // Verify and assert
        assertNotNull(response);
        assertEquals("Payment Successful", response);
        verify(webClient, times(1)).post();
    }

    // Test for processPayment (Failure)
    @Test
    public void testProcessPayment_Failure() {
        PaymentDetails mockPaymentDetails = new PaymentDetails();
        mockPaymentDetails.setAmount("100.00");

        // Mocking WebClient behavior using doThrow to simulate an exception
        doReturn(requestBodyUriSpec).when(webClient).post();
        doReturn(requestBodySpec).when(requestBodyUriSpec).uri("http://payment-service/payment/process");
        doReturn(requestHeadersSpec).when(requestBodySpec).bodyValue(mockPaymentDetails);
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.error(new RuntimeException("Payment Service Unavailable"))).when(responseSpec).bodyToMono(String.class);

        // Call the method and expect exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentService.processPayment(mockPaymentDetails);
        });

        // Verify and assert the exception message
        assertEquals("Failed to process payment.", exception.getMessage());
        verify(webClient, times(1)).post();
    }
}
