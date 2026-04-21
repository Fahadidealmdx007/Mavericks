package com.mavericks.controller;

import com.mavericks.model.CartItem;
import com.mavericks.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartRepository cartRepository;

    // POST /api/cart — add item
    @PostMapping
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, Object> body) {
        String sessionId   = (String) body.get("session_id");
        String productId   = (String) body.get("product_id");
        String productName = (String) body.getOrDefault("product_name", "");
        String size        = (String) body.get("size");
        String color       = (String) body.get("color");
        int    quantity    = body.containsKey("quantity") ? (int) body.get("quantity") : 1;
        double price       = body.containsKey("price") ? ((Number) body.get("price")).doubleValue() : 0;

        if (sessionId == null || productId == null || size == null || color == null) {
            return bad("Missing required fields");
        }

        // If same item exists, increase quantity
        Optional<CartItem> existing = cartRepository.findBySessionIdAndProductIdAndSizeAndColor(sessionId, productId, size, color);
        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartRepository.save(item);
        } else {
            cartRepository.save(CartItem.builder()
                .sessionId(sessionId).productId(productId).productName(productName)
                .size(size).color(color).quantity(quantity).price(price)
                .build());
        }

        List<CartItem> cart = cartRepository.findBySessionId(sessionId);
        return ResponseEntity.ok(Map.of("success", true, "message", "Added to cart", "cart", cart));
    }

    // GET /api/cart/{sessionId}
    @GetMapping("/{sessionId}")
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable String sessionId) {
        List<CartItem> items = cartRepository.findBySessionId(sessionId);
        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        return ResponseEntity.ok(Map.of("success", true, "data", items, "total", String.format("%.2f", total)));
    }

    // DELETE /api/cart/{id} — remove single item
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> removeItem(@PathVariable String id) {
        cartRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("success", true, "message", "Item removed"));
    }

    // DELETE /api/cart/session/{sessionId} — clear entire cart
    @Transactional
    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Map<String, Object>> clearCart(@PathVariable String sessionId) {
        cartRepository.deleteBySessionId(sessionId);
        return ResponseEntity.ok(Map.of("success", true, "message", "Cart cleared"));
    }

    private ResponseEntity<Map<String, Object>> bad(String msg) {
        return ResponseEntity.status(400).body(Map.of("success", false, "message", msg));
    }
}
