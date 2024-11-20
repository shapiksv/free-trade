package com.trade.free.dto.event;

import com.trade.free.dto.enums.EventConductorType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class EventConductorDto {

    private Integer senderUserId;
    private Integer receiverUserId;
    private EventConductorType type;
    private Integer transferId;
    private Integer itemId;
    private BigDecimal amount;
    private String description;
}
