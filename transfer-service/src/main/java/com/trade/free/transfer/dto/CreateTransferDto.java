package com.trade.free.transfer.dto;

import com.trade.free.transfer.enums.TransferType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransferDto {

    private Integer initiateUserId;
    private String receiverWalletNumber;
    private Integer itemId;
    private TransferType type;

}
