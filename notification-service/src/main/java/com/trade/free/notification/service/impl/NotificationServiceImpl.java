package com.trade.free.notification.service.impl;

import com.trade.free.dto.enums.EventNotificationType;
import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.notification.client.NotificationAdapterClient;
import com.trade.free.notification.config.MessageParams;
import com.trade.free.notification.entities.Message;
import com.trade.free.notification.mapper.NotificationMapper;
import com.trade.free.notification.repository.MessageRepository;
import com.trade.free.notification.service.NotificationService;
import com.trade.free.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.trade.free.notification.enums.ValueType.*;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final MessageRepository repository;
    private final MessageParams messageParams;
    private final NotificationMapper mapper;
    private final NotificationAdapterClient client;
    private final UserService userService;

    @Override
    public void create(EventNotificationDto event) {

        if (event.getType().equals(EventNotificationType.REGISTERED_USER)) {
            userService.create(event);
        }

        String contact = getContact(event);

        var subject = messageParams.getSubjectMessage().get(event.getType());
        var text = messageParams.getTextMessage().get(event.getType());
        var message = repository.save(Message.create(contact,
                setValue(subject, event),
                setValue(text, event)));
        client.send(mapper.mapToDto(message));
    }

    private String getContact(EventNotificationDto event) {
        if (event.getUserId() != null) {
            var optionUser = userService.getUserById(event.getUserId());
            return optionUser.isPresent() ? optionUser.get().getEmail() : event.getContact();
        }
        return event.getContact();
    }

    private String setValue(String text, EventNotificationDto event) {
        text = event.getCode() != null ? text.replace(CODE.name(), event.getCode()) : text;
        text = event.getFirstName() != null ? text.replace(FIRST_NAME.name(), event.getFirstName()) : text;
        text = event.getSecondName() != null ? text.replace(SECOND_NAME.name(), event.getSecondName()) : text;

        return text;
    }
}
