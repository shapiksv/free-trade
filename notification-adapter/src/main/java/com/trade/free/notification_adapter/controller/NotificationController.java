package com.trade.free.notification_adapter.controller;

import com.trade.free.dto.rest.notification.NotificationDtoReq;
import com.trade.free.notification_adapter.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/notification")
public class NotificationController {

    private final NotificationService service;

    @PostMapping(value = "/send")
    public ResponseEntity<Void> send(@RequestBody NotificationDtoReq req) {
        service.send(req);
        return ResponseEntity.noContent().build();
    }
}
