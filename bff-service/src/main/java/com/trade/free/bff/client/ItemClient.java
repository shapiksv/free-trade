package com.trade.free.bff.client;

import com.trade.free.dto.rest.item.ItemCreateReqDto;
import com.trade.free.dto.rest.item.ItemRespDto;
import com.trade.free.dto.rest.item.ItemUpdateReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "Item", url = "${client.url.item}")
public interface ItemClient {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ItemRespDto> create(@RequestHeader(value = "Authorization") String authorizationHeader,
                                       @RequestBody ItemCreateReqDto createReqDto);

    @PutMapping(value = "/{itemId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ItemRespDto> update(@RequestHeader(value = "Authorization") String authorizationHeader,
                                       @PathVariable("itemId") Integer itemId,
                                       @RequestBody ItemUpdateReqDto updateReqDto);

    @DeleteMapping(value = "/{itemId}")
    ResponseEntity<Void> delete(@RequestHeader(value = "Authorization") String authorizationHeader,
                                @PathVariable("itemId") Integer itemId);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<ItemRespDto>> getAll(@RequestHeader(value = "Authorization") String authorizationHeader);


    @GetMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<ItemRespDto>> getAvailable(@RequestHeader(value = "Authorization") String authorizationHeader);
}
