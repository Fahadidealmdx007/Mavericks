package com.mavericks.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String subject;

    @Column(length = 2000)
    private String message;

    @Builder.Default
    private String status = "unread";

    @Builder.Default
    private LocalDateTime sentAt = LocalDateTime.now();
}
