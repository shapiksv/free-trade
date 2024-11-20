package com.trade.free.notification.client;

import com.trade.free.dto.rest.notification.NotificationDtoReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${notification_adapter.name}", url = "${notification_adapter.url}")
public interface NotificationAdapterClient {

    @PostMapping(value = "/send")
    void send(NotificationDtoReq req);
}
