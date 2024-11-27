package com.trade.free.bff.controllers.impl;

import com.trade.free.bff.client.TransferClient;
import com.trade.free.bff.controllers.TransferController;
import com.trade.free.dto.rest.transfer.TransferRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class TransferControllerImpl implements TransferController {

    private final static String AUTHORIZATION = "Authorization";

    private final TransferClient transferClient;


    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransferRespDto>> getAll(@RequestHeader(name = AUTHORIZATION) String token) {
        var resp = transferClient.getAll(token);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @Override
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferRespDto> getById(@RequestHeader(name = AUTHORIZATION) String token,
                                                   @PathVariable Integer id) {
        var resp = transferClient.getById(token, id);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

}
