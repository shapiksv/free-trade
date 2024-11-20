package com.trade.free.dto.rest.item;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateReqDto {
    private String title;

    private String description;

    private String imageLinc;

    private BigDecimal price;

}
