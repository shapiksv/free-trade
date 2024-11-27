package com.trade.free.dto.rest.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletRespDto {
    private Integer id;
    private Integer userId;
    private String currency;
    private OffsetDateTime createdAt;
    private BigDecimal amount;
}
