package com.trade.free.item.dto;

import com.trade.free.item.enums.ItemStatus;
import lombok.Data;

@Data
public class ItemUpdateDto extends ItemCreateDto {

    private ItemStatus status;
}
