package com.trade.free.user.mapper;

import com.trade.free.dto.enums.EventNotificationType;
import com.trade.free.dto.enums.EventUserType;
import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.dto.event.EventUserDto;
import com.trade.free.dto.rest.user.SignUpReqDto;
import com.trade.free.dto.rest.user.UserRespDto;
import com.trade.free.user.dto.UserDto;
import com.trade.free.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    UserRespDto toResp(UserDto user);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(SignUpReqDto req);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "contact", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "secondName", source = "user.secondName")
    @Mapping(target = "type", source = "type")
    EventNotificationDto toNotificationEvent(User user, EventNotificationType type);


    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "secondName", source = "user.secondName")
    @Mapping(target = "type", source = "type")
    EventUserDto toUserEvent(User user, EventUserType type);

}
