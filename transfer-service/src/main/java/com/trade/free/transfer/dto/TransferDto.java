package com.trade.free.transfer.dto;

import com.trade.free.transfer.enums.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    private Integer id;
    private Integer senderUserId;
    private Integer receiverUserId;
    private Integer itemId;
    private String description;
    private OffsetDateTime createdAt;
    private TransferStatus status;
}
