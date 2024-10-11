package com.revature.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private String skuCode;
    private Double price;
    private Long categoryId; // Category ID to relate with Category
    private String imageUrl;
}
