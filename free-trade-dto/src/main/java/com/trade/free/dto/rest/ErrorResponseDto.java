package com.trade.free.dto.rest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private Integer code;
    private String errorMessage;
}
