package com.trade.free.transfer.entities;


import com.trade.free.transfer.dto.CreateTransferDto;
import com.trade.free.transfer.dto.UpdateTransferDto;
import com.trade.free.transfer.enums.TransferStatus;
import com.trade.free.transfer.enums.TransferType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.trade.free.transfer.enums.TransferStatus.CREATED;

@Data
@Table(name = "transfer")
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "sender_user_id", nullable = false)
    private Integer senderUserId;


    @Column(name = "receiver_user_id")
    private Integer receiverUserId;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransferStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransferType type;

    @Column(name = "amount")
    private BigDecimal amount;

    private Transfer() {
    }

    public static Transfer create(CreateTransferDto createTransferDto) {
        Transfer transfer = new Transfer();
        transfer.senderUserId = createTransferDto.getInitiateUserId();
        transfer.itemId = createTransferDto.getItemId();
        transfer.status = CREATED;
        transfer.createdAt = OffsetDateTime.now();

        return transfer;
    }

    public Transfer update(UpdateTransferDto updateTransferDto) {
        if (updateTransferDto.getAmount() != null) {
            this.amount = updateTransferDto.getAmount();
        }
        if (updateTransferDto.getStatus() != null) {
            this.status = updateTransferDto.getStatus();
        }
        if (updateTransferDto.getDescription() != null) {
            this.description = updateTransferDto.getDescription();
        }
        if (updateTransferDto.getReceiverUserId() != null) {
            this.receiverUserId = updateTransferDto.getReceiverUserId();
        }

        this.updatedAt = OffsetDateTime.now();
        return this;
    }

    public Transfer updateStatus(TransferStatus status) {
        this.status = status;
        this.updatedAt = OffsetDateTime.now();
        return this;
    }
}

