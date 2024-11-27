package com.trade.free.bff.client;

import com.trade.free.dto.rest.transfer.PurchaseRequestDto;
import com.trade.free.dto.rest.transfer.TransferRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@FeignClient(name = "Purchase", url = "${client.url.purchase}")
public interface PurchaseClient {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferRespDto> execute(@RequestHeader(value = "Authorization") String authorizationHeader,
                                                   @RequestBody PurchaseRequestDto requestDto);
}
