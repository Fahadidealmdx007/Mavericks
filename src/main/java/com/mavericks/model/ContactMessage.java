package com.mavericks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 2000)
    private String message;

    @Column(nullable = false)
    private String status = "unread";

    @Column(nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    public ContactMessage() {
    }

    public ContactMessage(String id, String name, String email, String subject, String message, String status, LocalDateTime sentAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.status = status == null ? "unread" : status;
        this.sentAt = sentAt == null ? LocalDateTime.now() : sentAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    @PrePersist
    public void prePersist() {
        if (status == null || status.isBlank()) {
            status = "unread";
        }
        if (sentAt == null) {
            sentAt = LocalDateTime.now();
        }
    }

    public static class Builder {
        private String id;
        private String name;
        private String email;
        private String subject;
        private String message;
        private String status = "unread";
        private LocalDateTime sentAt = LocalDateTime.now();

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder subject(String subject) { this.subject = subject; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder sentAt(LocalDateTime sentAt) { this.sentAt = sentAt; return this; }

        public ContactMessage build() {
            return new ContactMessage(id, name, email, subject, message, status, sentAt);
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
