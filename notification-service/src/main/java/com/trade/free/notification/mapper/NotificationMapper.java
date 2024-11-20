package com.trade.free.notification.mapper;

import com.trade.free.dto.rest.notification.NotificationDtoReq;
import com.trade.free.notification.entities.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDtoReq mapToDto(Message message);
}
