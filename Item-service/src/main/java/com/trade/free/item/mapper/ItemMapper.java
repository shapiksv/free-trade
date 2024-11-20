package com.trade.free.item.mapper;

import com.trade.free.dto.rest.item.ItemCreateReqDto;
import com.trade.free.dto.rest.item.ItemRespDto;
import com.trade.free.dto.rest.item.ItemUpdateReqDto;
import com.trade.free.item.dto.ItemCreateDto;
import com.trade.free.item.dto.ItemDto;
import com.trade.free.item.dto.ItemUpdateDto;
import com.trade.free.item.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {


    ItemDto mapToDto(Item item);

    ItemCreateDto mapCreateReqToDto(ItemCreateReqDto createReqDto);

    ItemUpdateDto mapUpdateReqToDto(ItemUpdateReqDto updateReqDto);

    @Mapping(target = "userId", source = "ownerId")
    ItemRespDto mapDtoToResp(ItemDto itemDto);

}
