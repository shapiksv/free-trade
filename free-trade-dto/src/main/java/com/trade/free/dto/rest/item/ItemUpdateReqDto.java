package com.trade.free.dto.rest.item;

import com.trade.free.dto.enums.ItemStatusDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateReqDto extends ItemCreateReqDto{
    private ItemStatusDto status;
}
