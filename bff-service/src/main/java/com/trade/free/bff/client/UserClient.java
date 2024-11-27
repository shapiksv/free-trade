package com.trade.free.bff.client;

import com.trade.free.dto.rest.user.UserRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "User", url = "${client.url.user}")
public interface UserClient {

    @GetMapping
    public ResponseEntity<UserRespDto> get(@RequestHeader(value = "Authorization") String authorizationHeader);
}
