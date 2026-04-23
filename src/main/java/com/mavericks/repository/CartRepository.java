package com.mavericks.repository;

import com.mavericks.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findBySessionId(String sessionId);
    Optional<CartItem> findBySessionIdAndProductIdAndSizeAndColor(String sessionId, String productId, String size, String color);
    void deleteBySessionId(String sessionId);
}
