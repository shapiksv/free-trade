
package com.trade.free.notification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.notification.service.NotificationService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaConsumer {

    private final NotificationService service;
    private final ObjectMapper mapper;

    public KafkaConsumer(NotificationService service,
                         ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @SneakyThrows
    @KafkaListener(topics = "${topic.notification}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTransferTopic(String message) {
        log.info("Event: [{}]", message);
        var event = mapper.readValue(message, EventNotificationDto.class);
        service.create(event);
    }
}
