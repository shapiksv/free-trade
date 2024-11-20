package com.trade.free.bff.client;

import com.trade.free.dto.rest.user.JwtAuthenticationRespDto;
import com.trade.free.dto.rest.user.OtpReqDto;
import com.trade.free.dto.rest.user.SignInReqDto;
import com.trade.free.dto.rest.user.SignUpReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "Auth", url = "${client.url.auth}")
public interface AuthClient {

    @PostMapping("/request-otp")
    ResponseEntity<Void> requestOtp(@RequestBody OtpReqDto req);

    @PostMapping("/sign-up")
    ResponseEntity<JwtAuthenticationRespDto> signUp(@RequestBody @Valid SignUpReqDto request);

    @PostMapping("/sign-in")
    ResponseEntity<JwtAuthenticationRespDto> signIn(@RequestBody @Valid SignInReqDto request);
}
