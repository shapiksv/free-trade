package com.trade.free.user.controllers;

import com.trade.free.dto.rest.user.UserRespDto;
import com.trade.free.user.env.BaseContext;
import com.trade.free.user.mapper.UserMapper;
import com.trade.free.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService service;

    private final UserMapper mapper;

    private final BaseContext context;

    @GetMapping
    public ResponseEntity<UserRespDto> get() {
        return ResponseEntity.ok().body(mapper.toResp(service.getById(context.getUserId())));
    }
}
