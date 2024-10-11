package com.revature.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {

    private Long id;
    private String name;
    private String skuCode;
    private Double price;
    private Integer quantity;

    // Getters and Setters
}