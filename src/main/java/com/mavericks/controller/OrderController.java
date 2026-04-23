package com.mavericks.controller;

import com.mavericks.model.Order;
import com.mavericks.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // POST /api/orders — place order
    @PostMapping
    public ResponseEntity<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> body) {
        String customerName = (String) body.get("customer_name");
        String email        = (String) body.get("email");
        String address      = (String) body.get("address");
        String productId    = (String) body.get("product_id");
        String productName  = (String) body.getOrDefault("product_name", "");
        String size         = (String) body.get("size");
        String color        = (String) body.get("color");
        int    quantity     = body.containsKey("quantity") ? (int) body.get("quantity") : 1;
        double total        = body.containsKey("total") ? Double.parseDouble(body.get("total").toString()) : 0;

        if (customerName == null || email == null || address == null || productId == null || size == null || color == null) {
            return bad("Please fill in all required fields");
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            return bad("Invalid email address");
        }

        Order order = orderRepository.save(Order.builder()
            .customerName(customerName).email(email).address(address)
            .productId(productId).productName(productName)
            .size(size).color(color).quantity(quantity).total(total)
            .build());

        return ResponseEntity.ok(Map.of("success", true, "message", "Order placed successfully!", "order_id", order.getId()));
    }

    // GET /api/orders — all orders (admin)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        List<Order> orders = orderRepository.findAllByOrderByCreatedAtDesc();
        return ResponseEntity.ok(Map.of("success", true, "data", orders, "count", orders.size()));
    }

    // PATCH /api/orders/{id}/status — update order status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        List<String> allowed = List.of("pending", "confirmed", "shipped", "delivered", "cancelled");

        if (!allowed.contains(status)) {
            return bad("Invalid status");
        }

        return orderRepository.findById(id).<ResponseEntity<Map<String, Object>>>map(order -> {
            order.setStatus(status);
            orderRepository.save(order);
            return ResponseEntity.ok(Map.of("success", true, "message", "Order updated to " + status));
        }).orElseGet(() -> bad("Order not found"));
    }

    private ResponseEntity<Map<String, Object>> bad(String msg) {
        return ResponseEntity.status(400).body(Map.of("success", false, "message", msg));
    }
}
