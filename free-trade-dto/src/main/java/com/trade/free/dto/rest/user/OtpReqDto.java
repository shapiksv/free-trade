package com.trade.free.dto.rest.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OtpReqDto {

    @Size(min = 5, max = 255, message = "Email address must be between 5 and 255 characters long")
    @NotBlank(message = "Email address cannot be empty")
    @Email(message = "The email address must be in the format user@example.com")
    private String email;
}
