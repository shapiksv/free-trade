package com.trade.free.bff.controllers.impl;

import com.trade.free.bff.client.WalletClient;
import com.trade.free.bff.controllers.WalletController;
import com.trade.free.dto.rest.wallet.WalletRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class WalletControllerImpl implements WalletController {

    private final static String AUTHORIZATION = "Authorization";

    private final WalletClient walletClient;


    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletRespDto> get(@RequestHeader(name = AUTHORIZATION) String token) {
        return walletClient.get(token);
    }
}
