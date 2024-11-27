package com.trade.free.bff.controllers.impl;

import com.trade.free.bff.client.ItemClient;
import com.trade.free.bff.controllers.ItemController;
import com.trade.free.dto.rest.item.ItemCreateReqDto;
import com.trade.free.dto.rest.item.ItemRespDto;
import com.trade.free.dto.rest.item.ItemUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class ItemControllerImpl implements ItemController {

    private final static String AUTHORIZATION = "Authorization";

    private final ItemClient itemClient;

    @Override
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRespDto> create(@RequestHeader(name = AUTHORIZATION) String token,
                                              @RequestBody ItemCreateReqDto createReqDto) {

        return itemClient.create(token, createReqDto);
    }

    @Override
    @PutMapping(value = "/{itemId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRespDto> update(@RequestHeader(name = AUTHORIZATION) String token,
                                              @PathVariable("itemId") Integer itemId,
                                              @RequestBody ItemUpdateReqDto updateReqDto) {
        var resp = itemClient.update(token, itemId, updateReqDto);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @Override
    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> delete(@RequestHeader(name = AUTHORIZATION) String token,
                                       @PathVariable("itemId") Integer itemId) {
        var resp = itemClient.delete(token, itemId);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemRespDto>> getAll(@RequestHeader(name = AUTHORIZATION) String token) {
        var resp = itemClient.getAll(token);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @Override
    @GetMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemRespDto>> getAvailable(@RequestHeader(name = AUTHORIZATION) String token) {
        var resp = itemClient.getAvailable(token);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}
