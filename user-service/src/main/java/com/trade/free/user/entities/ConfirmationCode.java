package com.trade.free.user.entities;

import com.trade.free.user.enums.ConfirmationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "confirmation_code")
public class ConfirmationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "attempt")
    private Integer attempt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ConfirmationStatus status;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    private ConfirmationCode() {
    }

    public static ConfirmationCode create(String email) {
        var confirmationCode = new ConfirmationCode();
        confirmationCode.setEmail(email);
        confirmationCode.setAttempt(0);
        confirmationCode.setStatus(ConfirmationStatus.NEW);
        confirmationCode.setCreatedAt(OffsetDateTime.now());

        return confirmationCode;
    }

    public void increaseAttempt() {
        this.attempt += 1;
    }
}
