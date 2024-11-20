package com.trade.free.bff.controllers;

import com.trade.free.dto.rest.user.UserExternalRespDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "api/v1/users")
@Tag(name = "User", description = "User Controller")
public interface UserController {

    @Operation(
            summary = "Get User.",
            tags = {"Transfer"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for get User",
                                                    implementation = UserExternalRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<UserExternalRespDto> get(String token);
}
