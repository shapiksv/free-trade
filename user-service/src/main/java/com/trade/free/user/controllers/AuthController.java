package com.trade.free.user.controllers;

import com.trade.free.dto.rest.user.JwtAuthenticationRespDto;
import com.trade.free.dto.rest.user.OtpReqDto;
import com.trade.free.dto.rest.user.SignInReqDto;
import com.trade.free.dto.rest.user.SignUpReqDto;
import com.trade.free.user.config.security.AuthenticationService;
import com.trade.free.user.services.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final ConfirmationService confirmationService;


    @PostMapping("/request-otp")
    public ResponseEntity<Void> requestOtp(@RequestBody OtpReqDto req) {
        confirmationService.createOtp(req.getEmail());
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationRespDto> signUp(@RequestBody @Valid SignUpReqDto request) {
        return ResponseEntity.ok().body(authenticationService.signUp(request));
    }


    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationRespDto> signIn(@RequestBody @Valid SignInReqDto request) {
        return ResponseEntity.ok().body(authenticationService.signIn(request));
    }
}
