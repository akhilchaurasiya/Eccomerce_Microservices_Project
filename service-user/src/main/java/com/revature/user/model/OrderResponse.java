package com.revature.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")  // Map this model to your 'orders' table in the database
public class OrderResponse {

    @Id
    private Long id;  // Primary key for the order

    private String productName;
    private int quantity;
    private double totalPrice;

    // Constructors
    public OrderResponse() {}

    public OrderResponse(Long id, String productName, int quantity, double totalPrice) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderResponse [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
    }
}