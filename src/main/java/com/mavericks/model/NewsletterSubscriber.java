package com.mavericks.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "newsletter")
public class NewsletterSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String email;

    private LocalDateTime subscribedAt = LocalDateTime.now();

    public NewsletterSubscriber() {
    }

    public NewsletterSubscriber(String id, String email, LocalDateTime subscribedAt) {
        this.id = id;
        this.email = email;
        this.subscribedAt = subscribedAt == null ? LocalDateTime.now() : subscribedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String email;
        private LocalDateTime subscribedAt = LocalDateTime.now();

        public Builder id(String id) { this.id = id; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder subscribedAt(LocalDateTime subscribedAt) { this.subscribedAt = subscribedAt; return this; }

        public NewsletterSubscriber build() {
            return new NewsletterSubscriber(id, email, subscribedAt);
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDateTime getSubscribedAt() { return subscribedAt; }
    public void setSubscribedAt(LocalDateTime subscribedAt) { this.subscribedAt = subscribedAt; }
}
