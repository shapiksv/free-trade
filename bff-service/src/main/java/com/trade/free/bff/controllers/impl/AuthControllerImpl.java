package com.trade.free.bff.controllers.impl;

import com.trade.free.bff.client.AuthClient;
import com.trade.free.bff.controllers.AuthController;
import com.trade.free.dto.rest.user.JwtAuthenticationRespDto;
import com.trade.free.dto.rest.user.OtpReqDto;
import com.trade.free.dto.rest.user.SignInReqDto;
import com.trade.free.dto.rest.user.SignUpReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {


    private final AuthClient userClient;

    @Override
    @PostMapping("/request-otp")
    public ResponseEntity<Void> requestOtp(@RequestBody OtpReqDto req) {

        return userClient.requestOtp(req);
    }

    @Override
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationRespDto> signUp(@RequestBody @Valid SignUpReqDto request) {
        return userClient.signUp(request);
    }

    @Override
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationRespDto> signIn(@RequestBody @Valid SignInReqDto request) {
        return userClient.signIn(request);
    }
}
