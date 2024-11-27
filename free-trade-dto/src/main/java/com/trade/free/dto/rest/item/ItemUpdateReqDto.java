package com.trade.free.dto.rest.item;

import com.trade.free.dto.enums.ItemStatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateReqDto extends ItemCreateReqDto {
    private ItemStatusDto status;
}
