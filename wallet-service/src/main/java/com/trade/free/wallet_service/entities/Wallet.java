package com.trade.free.wallet_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Table(name = "wallet")
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "user_id", nullable = false,  unique = true)
    private Integer userId;

    @Column(name = "serial", nullable = false, unique = true)
    private String serial;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "mutable_part_id", referencedColumnName = "id")
    private MutablePartOfWallet mutablePart;

    @Column(name = "currency")
    private String currency;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Version
    private Integer version;

    private Wallet() {
    }

    public static Wallet create(Integer userId,
                                String serial,
                                String currency) {
        Wallet wallet = new Wallet();
        wallet.userId = userId;
        wallet.createdAt = OffsetDateTime.now();
        wallet.serial = serial;
        wallet.currency = currency;
        MutablePartOfWallet.createEmpty(wallet);

        return wallet;
    }

    public boolean checkAvailableAmount(BigDecimal checkAmount) {
        return mutablePart.getAmount().compareTo(checkAmount) >= 0;
    }

    public void pushAmount(BigDecimal amount) {
        mutablePart.pushAmount(amount);
    }
}
