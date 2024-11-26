package com.trade.free.item.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemCreateDto {

    private String title;

    private String description;

    private String imageLinc;

    private BigDecimal price;

}
