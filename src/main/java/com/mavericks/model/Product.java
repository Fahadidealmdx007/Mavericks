package com.mavericks.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
