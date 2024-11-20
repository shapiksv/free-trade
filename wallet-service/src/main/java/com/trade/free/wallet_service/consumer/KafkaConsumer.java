
package com.trade.free.wallet_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.enums.EventConductorType;
import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.dto.event.EventUserDto;
import com.trade.free.dto.exception.TradeFreeMainException;
import com.trade.free.wallet_service.services.WalletService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.trade.free.dto.enums.EventConductorType.TRANSFER_AMOUNT_REQUEST;
import static com.trade.free.dto.enums.EventUserType.CREATE;


@Slf4j
@Component
public class KafkaConsumer {

    private final String conductorTopic;
    private final WalletService service;
    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaConsumer(@Value("${topic.conductor}") String conductorTopic,
                         WalletService service,
                         ObjectMapper mapper,
                         KafkaTemplate<String, String> kafkaTemplate) {
        this.conductorTopic = conductorTopic;
        this.service = service;
        this.mapper = mapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @SneakyThrows
    @KafkaListener(topics = "${topic.conductor}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTransferTopic(String message) {
        log.info("Event: [{}]", message);
        var event = mapper.readValue(message, EventConductorDto.class);
        if (TRANSFER_AMOUNT_REQUEST.equals(event.getType())) {
            try {
                service.transferAmount(event);
                sendEvent(event, EventConductorType.TRANSFER_AMOUNT_SUCCESSFULLY);
            } catch (TradeFreeMainException e) {
                log.warn("Something went wrong: ", e);
                event.setDescription(e.getErrorMessage());
                sendEvent(event, EventConductorType.TRANSFER_AMOUNT_FAILED);
            }
        }

    }

    @SneakyThrows
    @KafkaListener(topics = "${topic.user}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenUserTopic(String message) {
        log.info("Event: [{}]", message);
        var event = mapper.readValue(message, EventUserDto.class);
        if(CREATE.equals(event.getType())) {
            service.create(event.getUserId());
        }
    }

    @SneakyThrows
    private void sendEvent(EventConductorDto event, EventConductorType type) {
        event.setType(type);
        kafkaTemplate.send(conductorTopic, mapper.writeValueAsString(event));
    }
}
