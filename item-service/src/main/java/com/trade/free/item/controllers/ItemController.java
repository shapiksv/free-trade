package com.trade.free.item.controllers;

import com.trade.free.dto.rest.item.ItemCreateReqDto;
import com.trade.free.dto.rest.item.ItemRespDto;
import com.trade.free.dto.rest.item.ItemUpdateReqDto;
import com.trade.free.item.dto.ItemDto;
import com.trade.free.item.env.BaseContext;
import com.trade.free.item.mapper.ItemMapper;
import com.trade.free.item.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.trade.free.dto.permission.Permission.ITEM_OWNER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;
    private final ItemMapper mapper;
    private final BaseContext context;


    @Secured(ITEM_OWNER)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRespDto> create(@RequestBody ItemCreateReqDto createReqDto) {
        ItemDto itemDto = service.create(context.getUserId(), mapper.mapCreateReqToDto(createReqDto));
        return ResponseEntity.ok().body(mapper.mapDtoToResp(itemDto));
    }


    @Secured(ITEM_OWNER)
    @PutMapping(value = "/{itemId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRespDto> update(@PathVariable("itemId") Integer itemId,
                                              @RequestBody ItemUpdateReqDto updateReqDto) {
        ItemDto itemDto = service.update(context.getUserId(), itemId, mapper.mapUpdateReqToDto(updateReqDto));
        return ResponseEntity.ok().body(mapper.mapDtoToResp(itemDto));
    }


    @Secured(ITEM_OWNER)
    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable("itemId") Integer itemId) {
        service.delete(context.getUserId(), itemId);
        return ResponseEntity.noContent().build();
    }


    @Secured(ITEM_OWNER)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemRespDto>> getAll() {
        return ResponseEntity.ok().body(service.getByOwnerId(context.getUserId())
                .stream()
                .map(mapper::mapDtoToResp)
                .toList());
    }


    @Secured(ITEM_OWNER)
    @GetMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemRespDto>> getAvailable() {
        return ResponseEntity.ok().body(service.getAvailable(context.getUserId())
                .stream()
                .map(mapper::mapDtoToResp)
                .toList());
    }
}
