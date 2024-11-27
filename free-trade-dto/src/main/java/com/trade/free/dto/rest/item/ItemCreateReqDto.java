package com.trade.free.dto.rest.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
