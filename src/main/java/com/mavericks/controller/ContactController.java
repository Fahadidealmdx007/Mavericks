package com.mavericks.controller;

import com.mavericks.model.ContactMessage;
import com.mavericks.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactRepository contactRepository;

    // POST /api/contact — send message
    @PostMapping
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody Map<String, String> body) {
        String name    = body.get("name");
        String email   = body.get("email");
        String subject = body.getOrDefault("subject", "General Inquiry");
        String message = body.get("message");

        if (name == null || email == null || message == null) {
            return ResponseEntity.status(400).body(Map.of("success", false, "message", "Name, email and message are required"));
        }

        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            return ResponseEntity.status(400).body(Map.of("success", false, "message", "Invalid email address"));
        }

        contactRepository.save(ContactMessage.builder()
            .name(name).email(email).subject(subject).message(message)
            .build());

        return ResponseEntity.ok(Map.of("success", true, "message", "Message received. We'll get back to you soon."));
    }

    // GET /api/contact — all messages (admin)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getMessages() {
        List<ContactMessage> messages = contactRepository.findAllByOrderBySentAtDesc();
        return ResponseEntity.ok(Map.of("success", true, "data", messages, "count", messages.size()));
    }

    // PATCH /api/contact/{id}/read — mark as read
    @PatchMapping("/{id}/read")
    public ResponseEntity<Map<String, Object>> markRead(@PathVariable String id) {
        return contactRepository.findById(id).map(msg -> {
            msg.setStatus("read");
            contactRepository.save(msg);
            return ResponseEntity.ok(Map.of("success", true, "message", "Marked as read"));
        }).orElse(ResponseEntity.status(404).body(Map.of("success", false, "message", "Message not found")));
    }
}
