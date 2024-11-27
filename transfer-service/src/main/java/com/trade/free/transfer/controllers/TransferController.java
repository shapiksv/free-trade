package com.trade.free.transfer.controllers;

import com.trade.free.dto.rest.transfer.TransferRespDto;
import com.trade.free.transfer.env.BaseContext;
import com.trade.free.transfer.mapper.TransferMapper;
import com.trade.free.transfer.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.trade.free.dto.permission.Permission.TRANSFER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService service;
    private final TransferMapper mapper;
    private final BaseContext context;

    @Secured(TRANSFER)
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferRespDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.mapToRespDto(service.getById(id)));
    }

    @Secured(TRANSFER)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransferRespDto>> getAll() {
        return ResponseEntity.ok().body(service.findAllByUserId(context.getUserId())
                .stream()
                .map(mapper::mapToRespDto)
                .toList());
    }
}
