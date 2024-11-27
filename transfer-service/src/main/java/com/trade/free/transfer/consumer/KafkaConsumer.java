package com.trade.free.transfer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.transfer.services.ConductorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ConductorService service;
    private final ObjectMapper mapper;

    @SneakyThrows
    @KafkaListener(topics = "${topic.conductor}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.info("Event: [{}]", message);
        service.process(mapper.readValue(message, EventConductorDto.class));
    }
}
