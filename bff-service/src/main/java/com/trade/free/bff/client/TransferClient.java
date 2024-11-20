package com.trade.free.bff.client;

import com.trade.free.dto.rest.transfer.TransferRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "Transfer", url = "${client.url.transfer}")
public interface TransferClient {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransferRespDto>> getAll(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping(path = "/{id}",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferRespDto> getById(@RequestHeader(value = "Authorization") String authorizationHeader,
                                                   @PathVariable Integer id);
}
