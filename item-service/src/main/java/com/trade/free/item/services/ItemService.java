package com.trade.free.item.services;


import com.trade.free.item.dto.ItemCreateDto;
import com.trade.free.item.dto.ItemDto;
import com.trade.free.item.dto.ItemUpdateDto;

import java.util.List;

public interface ItemService {

    ItemDto create(Integer ownerId, ItemCreateDto itemCreateDto);

    ItemDto update(Integer ownerId, Integer itemId, ItemUpdateDto updateDto);

    void delete(Integer ownerId, Integer itemId);

    List<ItemDto> getByOwnerId(Integer ownerId);

    List<ItemDto> getAvailable(Integer ownerId);

    ItemDto holdItem(Integer itemId, Integer customerOwnerId);

    void unHoldItem(Integer itemId);

    void sell(Integer itemId, Integer newOwnerId);

}
