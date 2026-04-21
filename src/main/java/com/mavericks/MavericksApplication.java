package com.mavericks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MavericksApplication {
    public static void main(String[] args) {
        SpringApplication.run(MavericksApplication.class, args);
        System.out.println("\n🔥 MAVERICKS server → http://localhost:3000\n");
        System.out.println("  GET    /api/products");
        System.out.println("  POST   /api/cart");
        System.out.println("  GET    /api/cart/{sessionId}");
        System.out.println("  DELETE /api/cart/{id}");
        System.out.println("  POST   /api/orders");
        System.out.println("  GET    /api/orders");
        System.out.println("  POST   /api/newsletter");
        System.out.println("  GET    /api/newsletter\n");
    }
}
