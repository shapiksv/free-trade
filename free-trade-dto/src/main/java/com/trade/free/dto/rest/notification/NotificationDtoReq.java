package com.trade.free.dto.rest.notification;

import lombok.Data;

@Data
public class NotificationDtoReq {

    private String contact;
    private String subject;
    private String text;
}
