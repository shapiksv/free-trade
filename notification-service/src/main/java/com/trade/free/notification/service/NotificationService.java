package com.trade.free.notification.service;

import com.trade.free.dto.event.EventNotificationDto;

public interface NotificationService {

    void create(EventNotificationDto event);
}
