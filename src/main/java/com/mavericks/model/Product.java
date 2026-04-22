package com.mavericks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String category;
    private double price;
    private int stock;

    // Stored as comma-separated strings (e.g. "Onyx,Crimson,Emerald")
    private String colors;
    private String sizes;

    public Product() {
    }

    public Product(String id, String name, String category, double price, int stock, String colors, String sizes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.colors = colors;
        this.sizes = sizes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String category;
        private double price;
        private int stock;
        private String colors;
        private String sizes;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder category(String category) { this.category = category; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder stock(int stock) { this.stock = stock; return this; }
        public Builder colors(String colors) { this.colors = colors; return this; }
        public Builder sizes(String sizes) { this.sizes = sizes; return this; }

        public Product build() {
            return new Product(id, name, category, price, stock, colors, sizes);
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getColors() { return colors; }
    public void setColors(String colors) { this.colors = colors; }
    public String getSizes() { return sizes; }
    public void setSizes(String sizes) { this.sizes = sizes; }
}
