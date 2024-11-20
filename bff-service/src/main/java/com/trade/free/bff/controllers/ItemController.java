package com.trade.free.bff.controllers;

import com.trade.free.dto.rest.item.ItemCreateReqDto;
import com.trade.free.dto.rest.item.ItemRespDto;
import com.trade.free.dto.rest.item.ItemUpdateReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "api/v1/items")
@Tag(name = "Item", description = "Item Controller")
public interface ItemController {

    @Operation(
            summary = "Create new items.",
            tags = {"Item"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for create new items",
                                                    implementation = ItemRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<ItemRespDto> create(String token,
                                       ItemCreateReqDto createReqDto);

    @Operation(
            summary = "Update items.",
            tags = {"Item"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for update items",
                                                    implementation = ItemRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<ItemRespDto> update(String token,
                                       Integer itemId,
                                       ItemUpdateReqDto updateReqDto);

    @Operation(
            summary = "Create new items.",
            tags = {"Item"},
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for create new items",
                                                    implementation = Void.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<Void> delete(String token,
                                Integer itemId);

    @Operation(
            summary = "Get all your items.",
            tags = {"Item"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for get all your items",
                                                    implementation = ItemRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<List<ItemRespDto>> getAll(String token);

    @Operation(
            summary = "Get all your items.",
            tags = {"Item"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    description = "Endpoint for get all your items",
                                                    implementation = ItemRespDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    ResponseEntity<List<ItemRespDto>> getAvailable(String token);
}
