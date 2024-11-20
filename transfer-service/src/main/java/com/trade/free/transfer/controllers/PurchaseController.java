package com.trade.free.transfer.controllers;

import com.trade.free.dto.rest.transfer.PurchaseRequestDto;
import com.trade.free.dto.rest.transfer.TransferRespDto;
import com.trade.free.transfer.env.BaseContext;
import com.trade.free.transfer.mapper.TransferMapper;
import com.trade.free.transfer.services.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trade.free.dto.permission.Permission.TRANSFER;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final ConductorService service;
    private final TransferMapper mapper;
    private final BaseContext context;


    @Secured(TRANSFER)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferRespDto> execute(@RequestBody PurchaseRequestDto requestDto) {
        return ResponseEntity.ok().body(mapper.mapToRespDto(service.purchaseOfItem(context.getUserId(), requestDto.getItemId())));
    }
}
