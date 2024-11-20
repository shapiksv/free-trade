package com.trade.free.bff.controllers;

import com.trade.free.dto.rest.user.JwtAuthenticationRespDto;
import com.trade.free.dto.rest.user.OtpReqDto;
import com.trade.free.dto.rest.user.SignInReqDto;
import com.trade.free.dto.rest.user.SignUpReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "/api/v1/auth")
@Tag(name = "User", description = "User Controller")
public interface AuthController {


    @Operation(
            summary = "Request OTP.",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for request OTP",
                                                    implementation = Void.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<Void> requestOtp(OtpReqDto req);

    @Operation(
            summary = "Registration.",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for registration new user",
                                                    implementation = JwtAuthenticationRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<JwtAuthenticationRespDto> signUp(SignUpReqDto request);

    @Operation(
            summary = "Login.",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for login user",
                                                    implementation = JwtAuthenticationRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<JwtAuthenticationRespDto> signIn(SignInReqDto request);
}
