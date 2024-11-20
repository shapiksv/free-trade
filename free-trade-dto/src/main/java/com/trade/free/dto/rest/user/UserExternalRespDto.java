package com.trade.free.dto.rest.user;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class UserExternalRespDto {

    private String username;

    private String firstName;

    private String secondName;

    private String email;

    private OffsetDateTime dateOfBirthday;

    private BigDecimal amount;

    private Integer numberOfItems;
}
