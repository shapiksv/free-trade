package com.trade.free.wallet_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "mutable_part_of_wallet")
public class MutablePartOfWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "wallet_Id")
    private Wallet wallet;

    @Column(precision = 21, scale = 4)
    private BigDecimal amount;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;


    private MutablePartOfWallet() {

    }

    public static void createEmpty(Wallet wallet) {
        MutablePartOfWallet mutablePartOfWallet = new MutablePartOfWallet();
        mutablePartOfWallet.amount = BigDecimal.ZERO;
        mutablePartOfWallet.createdAt = OffsetDateTime.now();
        mutablePartOfWallet.wallet = wallet;
        wallet.setMutablePart(mutablePartOfWallet);
    }

    public void pushAmount(BigDecimal amount) {
        MutablePartOfWallet copy = copy();
        copy.setAmount(amount.add(copy.getAmount()));
    }

    private MutablePartOfWallet copy() {
        MutablePartOfWallet mutablePartOfWallet = new MutablePartOfWallet();
        mutablePartOfWallet.amount = this.amount;
        mutablePartOfWallet.createdAt = OffsetDateTime.now();
        mutablePartOfWallet.wallet = this.wallet;
        this.wallet.setMutablePart(mutablePartOfWallet);
        return mutablePartOfWallet;
    }

    @Override
    public String toString() {
        return "MutablePartOfWallet{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
