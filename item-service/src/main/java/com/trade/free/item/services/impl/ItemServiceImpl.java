package com.trade.free.item.services.impl;


import com.trade.free.item.dto.ItemCreateDto;
import com.trade.free.item.dto.ItemDto;
import com.trade.free.item.dto.ItemUpdateDto;
import com.trade.free.item.entities.Item;
import com.trade.free.item.enums.ItemStatus;
import com.trade.free.item.exception.ItemAlreadyYourException;
import com.trade.free.item.exception.ItemNotAvailableException;
import com.trade.free.item.exception.ItemNotFoundException;
import com.trade.free.item.exception.WrongStatusException;
import com.trade.free.item.mapper.ItemMapper;
import com.trade.free.item.repository.ItemRepository;
import com.trade.free.item.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.trade.free.item.enums.ItemStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    private final ItemRepository repository;
    private final ItemMapper itemMapper;


    @Override
    public ItemDto create(Integer ownerId, ItemCreateDto itemCreateDto) {
        return itemMapper.mapToDto(repository.save(Item.create(ownerId, itemCreateDto)));
    }

    @Override
    public ItemDto update(Integer ownerId, Integer itemId, ItemUpdateDto updateDto) {
        var item = repository.findByOwnerIdAndId(ownerId, itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
        if (!List.of(AVAILABLE, NOT_AVAILABLE).contains(item.getStatus()))
            throw new WrongStatusException();

        return itemMapper.mapToDto(repository.save(item.update(updateDto)));
    }

    @Override
    public void delete(Integer ownerId, Integer itemId) {
        repository.findByOwnerIdAndId(ownerId, itemId).ifPresent(item -> repository.save(item.marcAsDelete()));
    }

    @Override
    public List<ItemDto> getByOwnerId(Integer ownerId) {
        return repository.findAllByOwnerId(ownerId).stream().map(itemMapper::mapToDto).toList();
    }

    @Override
    public List<ItemDto> getAvailable(Integer ownerId) {
        return repository.findAllByOwnerIdNotAndStatus(ownerId, AVAILABLE).stream().map(itemMapper::mapToDto).toList();
    }

    @Override
    public ItemDto holdItem(Integer itemId, Integer customerOwnerId) {
        var item = repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        validate(item, customerOwnerId, AVAILABLE);
        return itemMapper.mapToDto(repository.save(item.marcAsHold()));
    }

    @Override
    public void unHoldItem(Integer itemId) {
        var item = repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        itemMapper.mapToDto(repository.save(item.marcAsAvailable()));
    }

    @Override
    public void sell(Integer itemId, Integer newOwnerId) {
        var item = repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        validate(item, newOwnerId, HOLD);
        repository.save(item.assignNewOwner(newOwnerId));
    }

    private void validate(Item item, Integer customerOwnerId, ItemStatus status) {

        if (item.getOwnerId().equals(customerOwnerId)) {
            throw new ItemAlreadyYourException(item.getId());
        }

        if (!item.getStatus().equals(status)) {
            throw new ItemNotAvailableException(item.getId(), item.getStatus(), status);
        }
    }
}
