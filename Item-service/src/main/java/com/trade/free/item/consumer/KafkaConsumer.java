package com.trade.free.item.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.enums.EventConductorType;
import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.dto.exception.TradeFreeMainException;
import com.trade.free.item.dto.ItemDto;
import com.trade.free.item.services.ItemService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.trade.free.dto.enums.EventConductorType.SELL_ITEM_SUCCESSFULLY;

@Slf4j
@Component
public class KafkaConsumer {

    private final String topic;
    private final ItemService service;
    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaConsumer(@Value("${topic.conductor}") String topic,
                         ItemService service,
                         ObjectMapper mapper,
                         KafkaTemplate<String, String> kafkaTemplate) {
        this.topic = topic;
        this.service = service;
        this.mapper = mapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @SneakyThrows
    @KafkaListener(topics = "${topic.conductor}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.info("Event: [{}]", message);
        
        process(mapper.readValue(message, EventConductorDto.class));
    }

    private void process(EventConductorDto event) {
        
        switch (event.getType()) {
            case HOLD_ITEM_REQUEST -> holdItem(event);
            case UN_HOLD_ITEM_REQUEST-> unHoldItem(event);
            case SELL_ITEM_REQUEST -> sellItem(event);
        }
    }

    @SneakyThrows
    private void holdItem(EventConductorDto event) {
        try {
            ItemDto itemDto = service.holdItem(event.getItemId(), event.getSenderUserId());
            event.setType(EventConductorType.HOLD_ITEM_SUCCESSFULLY);
            event.setReceiverUserId(itemDto.getOwnerId());
            event.setAmount(itemDto.getPrice());
            kafkaTemplate.send(topic, mapper.writeValueAsString(event));

        } catch (TradeFreeMainException e) {
            log.warn("Something went wrong", e);
            event.setType(EventConductorType.HOLD_ITEM_FAILED);
            event.setDescription(e.getErrorMessage());
            kafkaTemplate.send(topic, mapper.writeValueAsString(event));
        }
    }

    private void unHoldItem(EventConductorDto event) {
        service.unHoldItem(event.getItemId());
    }

    @SneakyThrows
    private void sellItem(EventConductorDto event) {
        service.sell(event.getItemId(), event.getSenderUserId());
        event.setType(SELL_ITEM_SUCCESSFULLY);
        kafkaTemplate.send(topic, mapper.writeValueAsString(event));
    }
}
