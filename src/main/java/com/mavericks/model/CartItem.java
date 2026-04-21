package com.mavericks.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String sessionId;
    private String productId;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private double price;

    @Builder.Default
    private LocalDateTime addedAt = LocalDateTime.now();
}
