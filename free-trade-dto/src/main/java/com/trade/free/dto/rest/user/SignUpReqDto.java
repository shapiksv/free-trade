package com.trade.free.dto.rest.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
public class SignUpReqDto {

    @NotNull(message = "OTP cannot be null")
    @NotBlank(message = "OTP cannot be empty")
    private String otp;

    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters long")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    private String firstName;

    private String secondName;

    @Size(min = 5, max = 255, message = "Email address must be between 5 and 255 characters long")
    @NotBlank(message = "Email address cannot be empty")
    @Email(message = "The email address must be in the format user@example.com")
    private String email;

    @Size(min = 8, max = 255, message = "Password length must be between 8 and 255 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    private OffsetDateTime dateOfBirthday;
}
