package com.trade.free.user.dto;

import com.trade.free.user.enums.Role;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserDto {

    private String username;

    private String firstName;

    private String secondName;

    private String email;

    private String password;

    private OffsetDateTime dateOfBirthday;

    private Role role;
}
