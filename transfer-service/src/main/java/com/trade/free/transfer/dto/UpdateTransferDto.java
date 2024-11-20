package com.trade.free.transfer.dto;

import com.trade.free.transfer.enums.TransferStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UpdateTransferDto {

    private BigDecimal amount;
    private TransferStatus status;
    private String description;
    private Integer receiverUserId;
    private Integer itemId;
}
