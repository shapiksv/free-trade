package com.trade.free.bff.controllers.impl;

import com.trade.free.bff.client.PurchaseClient;
import com.trade.free.bff.controllers.PurchaseController;
import com.trade.free.dto.rest.transfer.PurchaseRequestDto;
import com.trade.free.dto.rest.transfer.TransferRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class PurchaseControllerImpl implements PurchaseController {

    private final static String AUTHORIZATION = "Authorization";

    private final PurchaseClient purchaseClient;

    @Override
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferRespDto> execute(@RequestHeader(name = AUTHORIZATION) String token,
                                                   @RequestBody PurchaseRequestDto requestDto) {
        return purchaseClient.execute(token, requestDto);
    }
}
