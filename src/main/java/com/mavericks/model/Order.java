package com.mavericks.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Builder.Default
    private String status = "pending";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
