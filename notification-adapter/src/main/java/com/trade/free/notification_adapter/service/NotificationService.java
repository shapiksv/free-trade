package com.trade.free.notification_adapter.service;

import com.trade.free.dto.rest.notification.NotificationDtoReq;

public interface NotificationService {

    void send(NotificationDtoReq req);
}
