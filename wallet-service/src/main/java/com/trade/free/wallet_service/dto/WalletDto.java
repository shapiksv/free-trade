package com.trade.free.wallet_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class WalletDto {
    private Integer id;
    private Integer userId;
    private String currency;
    private OffsetDateTime createdAt;
    private BigDecimal amount;
}
