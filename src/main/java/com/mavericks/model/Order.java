package com.mavericks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String customerName;
    private String email;
    private String address;

    private String productId;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private double total;

    private String status = "pending";

    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {
    }

    public Order(String id, String customerName, String email, String address, String productId, String productName,
                 String size, String color, int quantity, double total, String status, LocalDateTime createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.total = total;
        this.status = status == null ? "pending" : status;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String customerName;
        private String email;
        private String address;
        private String productId;
        private String productName;
        private String size;
        private String color;
        private int quantity;
        private double total;
        private String status = "pending";
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(String id) { this.id = id; return this; }
        public Builder customerName(String customerName) { this.customerName = customerName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder productId(String productId) { this.productId = productId; return this; }
        public Builder productName(String productName) { this.productName = productName; return this; }
        public Builder size(String size) { this.size = size; return this; }
        public Builder color(String color) { this.color = color; return this; }
        public Builder quantity(int quantity) { this.quantity = quantity; return this; }
        public Builder total(double total) { this.total = total; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Order build() {
            return new Order(id, customerName, email, address, productId, productName, size, color, quantity, total, status, createdAt);
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
