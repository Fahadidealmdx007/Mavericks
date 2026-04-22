package com.mavericks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    public CartItem() {
    }

    public CartItem(String id, String sessionId, String productId, String productName, String size, String color,
                    int quantity, double price, LocalDateTime addedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.addedAt = addedAt == null ? LocalDateTime.now() : addedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    @PrePersist
    public void prePersist() {
        if (addedAt == null) {
            addedAt = LocalDateTime.now();
        }
    }

    public static class Builder {
        private String id;
        private String sessionId;
        private String productId;
        private String productName;
        private String size;
        private String color;
        private int quantity;
        private double price;
        private LocalDateTime addedAt = LocalDateTime.now();

        public Builder id(String id) { this.id = id; return this; }
        public Builder sessionId(String sessionId) { this.sessionId = sessionId; return this; }
        public Builder productId(String productId) { this.productId = productId; return this; }
        public Builder productName(String productName) { this.productName = productName; return this; }
        public Builder size(String size) { this.size = size; return this; }
        public Builder color(String color) { this.color = color; return this; }
        public Builder quantity(int quantity) { this.quantity = quantity; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder addedAt(LocalDateTime addedAt) { this.addedAt = addedAt; return this; }

        public CartItem build() {
            return new CartItem(id, sessionId, productId, productName, size, color, quantity, price, addedAt);
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
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
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
