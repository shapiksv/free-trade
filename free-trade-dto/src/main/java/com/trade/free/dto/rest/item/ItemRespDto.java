package com.trade.free.dto.rest.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRespDto extends ItemCreateReqDto {
    private Integer id;
    private String status;
    private Integer userId;
}
