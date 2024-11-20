package com.trade.free.bff.controllers;

import com.trade.free.dto.rest.transfer.TransferRespDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "api/v1/transfers")
@Tag(name = "Transfer", description = "Transfer Controller")
public interface TransferController {

    @Operation(
            summary = "Get all.",
            tags = {"Transfer"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for get all transfers",
                                                    implementation = TransferRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<List<TransferRespDto>> getAll(String token);

    @Operation(
            summary = "Get by id.",
            tags = {"Transfer"},
            parameters = {@Parameter(name = "id",
                    in = ParameterIn.PATH,
                    description = "ID",
                    required = true)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for get transfer by id",
                                                    implementation = TransferRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<TransferRespDto> getById(String token,
                                            Integer id);
}
