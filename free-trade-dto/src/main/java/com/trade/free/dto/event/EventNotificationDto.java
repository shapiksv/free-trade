package com.trade.free.dto.event;

import com.trade.free.dto.enums.EventNotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventNotificationDto {

    private Integer userId;

    private String contact;

    private String firstName;

    private String secondName;

    private String code;

    private EventNotificationType type;
}
