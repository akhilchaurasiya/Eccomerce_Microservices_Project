package com.revature.user.service;

import com.revature.user.model.ProductRequest;
import com.revature.user.model.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.Locale.Category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for getAllProducts (Success)
    @Test
    public void testGetAllProducts_Success() {
        List<ProductResponse> mockProducts = Arrays.asList(
                new ProductResponse(),
                new ProductResponse()
        );

        // Mocking the WebClient call chain
        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri("http://localhost:8087/products");
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Flux.fromIterable(mockProducts)).when(responseSpec).bodyToFlux(ProductResponse.class);

        List<ProductResponse> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getName());
        verify(webClientBuilder, times(1)).build();
    }

    @Test
    public void testGetAllProducts_Failure() {
        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri("http://localhost:8087/products");
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doThrow(new RuntimeException("Failed to fetch products")).when(responseSpec).bodyToFlux(ProductResponse.class);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getAllProducts();
        });

        assertEquals("Failed to fetch products", exception.getMessage());
    }

    // Test for getProductById (Success)
    @Test
    public void testGetProductById_Success() {
        Long productId = 1L;
        ProductResponse mockProduct = new ProductResponse();

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri("http://localhost:8087/products/" + productId);
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just(mockProduct)).when(responseSpec).bodyToMono(ProductResponse.class);

        ProductResponse result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals("Product1", result.getName());
        verify(webClientBuilder, times(1)).build();
    }

    // Test for getProductById (Failure)
    @Test
    public void testGetProductById_Failure() {
        Long productId = 1L;

        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri("http://localhost:8087/products/" + productId);
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doThrow(new RuntimeException("Failed to fetch product by ID")).when(responseSpec).bodyToMono(ProductResponse.class);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(productId);
        });

        assertEquals("Failed to fetch product by ID", exception.getMessage());
    }

    // Test for addProduct (Success)
    @Test
    public void testAddProduct_Success() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("NewProduct");

        // Mocking the WebClient call chain for POST
        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestBodySpec).when(webClient).post();
        ((UriSpec<?>) doReturn(requestHeadersSpec).when(requestBodySpec)).uri("http://localhost:8087/products");
        ((RequestBodySpec) doReturn(requestHeadersSpec).when(requestHeadersSpec)).bodyValue(productRequest);
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(Mono.just("Product added successfully")).when(responseSpec).bodyToMono(String.class);

        String result = productService.addProduct(productRequest);

        assertEquals("Product added successfully", result);
        verify(webClientBuilder, times(1)).build();
    }

    // Test for addProduct (Failure)
//    @Test
//    public void testAddProduct_Failure() {
//        ProductRequest productRequest = new ProductRequest();
//        productRequest.setName("NewProduct");
//
//        // Mocking the WebClient call chain for POST failure
//        doReturn(webClient).when(webClientBuilder).build();
//        doReturn(requestBodySpec).when(webClient).post();
//        doReturn(requestHeadersSpec).when(requestBodySpec).uri("http://localhost:8087/products");
//        doReturn(requestHeadersSpec).when(requestHeadersSpec).bodyValue(productRequest);
//        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
//        doThrow(new RuntimeException("Failed to add product")).when(responseSpec).bodyToMono(String.class);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            productService.addProduct(productRequest);
//        });
//
//        assertEquals("Failed to add product", exception.getMessage());
//    }
//
//    // Test for getAllCategories (Success)
//    @Test
//    public void testGetAllCategories_Success() {
//        List<Category> mockCategories = Arrays.asList(
//                new Category("Category1"),
//                new Category("Category2")
//        );
//
//        doReturn(webClient).when(webClientBuilder).build();
//        doReturn(requestHeadersUriSpec).when(webClient).get();
//        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri("http://localhost:8087/categories");
//        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
//        doReturn(Flux.fromIterable(mockCategories)).when(responseSpec).bodyToFlux(Category.class);
//
//        List<Category> result = productService.getAllCategories();
//
//        assertEquals(2, result.size());
//        assertEquals("Category1", result.get(0).getDisplayName());
//        verify(webClientBuilder, times(1)).build();
//    }

    // Test for getAllCategories (Failure)
    @Test
    public void testGetAllCategories_Failure() {
        doReturn(webClient).when(webClientBuilder).build();
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri("http://localhost:8087/categories");
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doThrow(new RuntimeException("Failed to fetch categories")).when(responseSpec).bodyToFlux(Category.class);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getAllCategories();
        });

        assertEquals("Failed to fetch categories", exception.getMessage());
    }
}
