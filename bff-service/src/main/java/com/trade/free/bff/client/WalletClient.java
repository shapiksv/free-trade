package com.trade.free.bff.client;

import com.trade.free.dto.rest.wallet.WalletRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "Wallet", url = "${client.url.wallet}")
public interface WalletClient {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WalletRespDto> get(@RequestHeader(value = "Authorization") String authorizationHeader);
}
