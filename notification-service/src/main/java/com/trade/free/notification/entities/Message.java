package com.trade.free.notification.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "contact")
    private String contact;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    private Message() {
    }

    public static Message create(String contact,
                                 String subject,
                                 String text) {
        var message = new Message();
        message.setContact(contact);
        message.setSubject(subject);
        message.setText(text);
        message.setCreatedAt(OffsetDateTime.now());

        return message;
    }
}
