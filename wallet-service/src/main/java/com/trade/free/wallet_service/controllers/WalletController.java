package com.trade.free.wallet_service.controllers;

import com.trade.free.dto.rest.wallet.WalletRespDto;
import com.trade.free.wallet_service.env.BaseContext;
import com.trade.free.wallet_service.mapper.WalletMapper;
import com.trade.free.wallet_service.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trade.free.dto.permission.Permission.WALLET_OWNER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService service;
    private final WalletMapper mapper;
    private final BaseContext context;

    @Secured(WALLET_OWNER)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletRespDto> get() {
        return ResponseEntity.ok().body(mapper.mapDtoToRESP(service.getByUserId(context.getUserId())));
    }
}
