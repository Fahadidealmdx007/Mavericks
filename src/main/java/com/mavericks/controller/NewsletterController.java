package com.mavericks.controller;

import com.mavericks.model.NewsletterSubscriber;
import com.mavericks.repository.NewsletterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = "*")
public class NewsletterController {

    private final NewsletterRepository newsletterRepository;

    public NewsletterController(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    // POST /api/newsletter — subscribe
    @PostMapping
    public ResponseEntity<Map<String, Object>> subscribe(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        if (email == null || email.isBlank()) {
            return bad("Email is required");
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            return bad("Invalid email address");
        }

        if (newsletterRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.status(409).body(Map.of("success", false, "message", "You're already on the list!"));
        }

        newsletterRepository.save(NewsletterSubscriber.builder().email(email).build());
        return ResponseEntity.ok(Map.of("success", true, "message", "You're on the list. Heat incoming."));
    }

    // GET /api/newsletter — all subscribers (admin)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSubscribers() {
        List<NewsletterSubscriber> subs = newsletterRepository.findAll();
        return ResponseEntity.ok(Map.of("success", true, "data", subs, "count", subs.size()));
    }

    private ResponseEntity<Map<String, Object>> bad(String msg) {
        return ResponseEntity.status(400).body(Map.of("success", false, "message", msg));
    }
}
