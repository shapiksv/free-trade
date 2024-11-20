package com.trade.free.dto.rest.item;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRespDto extends ItemCreateReqDto{
    private Integer id;
    private String status;
    private Integer userId;
}
