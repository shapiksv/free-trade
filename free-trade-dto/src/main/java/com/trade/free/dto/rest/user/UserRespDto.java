package com.trade.free.dto.rest.user;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserRespDto {

    private String username;

    private String firstName;

    private String secondName;

    private String email;

    private OffsetDateTime dateOfBirthday;

}
