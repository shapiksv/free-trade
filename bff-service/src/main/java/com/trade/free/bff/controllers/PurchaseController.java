package com.trade.free.bff.controllers;

import com.trade.free.dto.rest.transfer.PurchaseRequestDto;
import com.trade.free.dto.rest.transfer.TransferRespDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "api/v1/purchase")
@Tag(name = "Purchase", description = "Purchase Controller")
public interface PurchaseController {

    @Operation(
            summary = "Purchase Request",
            tags = {"Purchase"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for purchase request",
                                                    implementation = TransferRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<TransferRespDto> execute(String token,
                                            PurchaseRequestDto requestDto);
}
