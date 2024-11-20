package com.trade.free.bff.controllers.impl;

import com.trade.free.bff.controllers.UserController;
import com.trade.free.bff.service.UserService;
import com.trade.free.dto.rest.user.UserExternalRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final static String AUTHORIZATION = "Authorization";

    private final UserService service;

    @Override
    @GetMapping
    public ResponseEntity<UserExternalRespDto> get(@RequestHeader(name = AUTHORIZATION) String token) {
        return ResponseEntity.ok().body(service.getUser(token));
    }
}
