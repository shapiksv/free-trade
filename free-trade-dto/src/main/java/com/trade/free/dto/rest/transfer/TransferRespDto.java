package com.trade.free.dto.rest.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRespDto {
    private Integer id;
    private Integer senderUserId;
    private Integer receiverUserId;
    private Integer itemId;
    private String description;
    private OffsetDateTime createdAt;
    private String status;
}
