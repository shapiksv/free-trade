package com.trade.free.dto.rest.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignInReqDto {
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters long")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 8, max = 255, message = "Password length must be between 8 and 255 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
