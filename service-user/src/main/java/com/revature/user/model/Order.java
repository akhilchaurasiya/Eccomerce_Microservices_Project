package com.revature.user.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private String orderNumber;
    private String userId;
    private String status;
    private List<OrderLineItems> orderLineItems;
    private String billingAddress;
    private String shippingAddress;

    // Getters and Setters
}