package com.trade.free.dto.rest.transfer;

import lombok.*;

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
