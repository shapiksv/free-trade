package com.trade.free.notification.service;

import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.notification.entities.User;

import java.util.Optional;

public interface UserService {
    void create(EventNotificationDto event);

    Optional<User> getUserById(Integer id);
}
