package com.trade.free.item.dto;

import com.trade.free.item.enums.ItemStatus;
import lombok.Data;

@Data
public class ItemDto extends ItemCreateDto{

    private Integer id;
    private ItemStatus status;
    private Integer ownerId;
}
