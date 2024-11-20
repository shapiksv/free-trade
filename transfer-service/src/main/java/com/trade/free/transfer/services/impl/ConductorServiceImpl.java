package com.trade.free.transfer.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.enums.EventConductorType;
import com.trade.free.dto.enums.EventNotificationType;
import com.trade.free.dto.event.EventConductorDto;
import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.transfer.dto.CreateTransferDto;
import com.trade.free.transfer.dto.TransferDto;
import com.trade.free.transfer.dto.UpdateTransferDto;
import com.trade.free.transfer.enums.TransferStatus;
import com.trade.free.transfer.services.ConductorService;
import com.trade.free.transfer.services.TransferService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.trade.free.dto.enums.EventNotificationType.PURCHASE_FAILED;
import static com.trade.free.dto.enums.EventNotificationType.PURCHASE_SUCCESSFUL;

@Service
public class ConductorServiceImpl implements ConductorService {

    private final String conductorTopic;
    private final String notificationTopic;
    private final TransferService transferService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ConductorServiceImpl(@Value("${topic.conductor}") String conductorTopic,
                                @Value("${topic.notification}") String notificationTopic,
                                TransferService transferService,
                                KafkaTemplate<String, String> kafkaTemplate,
                                ObjectMapper objectMapper) {
        this.conductorTopic = conductorTopic;
        this.notificationTopic = notificationTopic;
        this.transferService = transferService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public TransferDto purchaseOfItem(Integer userId, Integer itemId) {

        var transfer = transferService.create(CreateTransferDto.builder()
                                                               .initiateUserId(userId)
                                                               .itemId(itemId)
                                                               .build());
        var event = EventConductorDto.builder()
                                     .itemId(itemId)
                                     .senderUserId(userId)
                                     .transferId(transfer.getId())
                                     .build();

        sendConductorEvent(event, EventConductorType.HOLD_ITEM_REQUEST);
        return transfer;
    }

    @Override
    public void process(EventConductorDto event) {
        switch (event.getType()) {
            case HOLD_ITEM_FAILED -> holdItemFailed(event);
            case HOLD_ITEM_SUCCESSFULLY -> transferAmount(event);
            case TRANSFER_AMOUNT_SUCCESSFULLY -> sellItem(event);
            case TRANSFER_AMOUNT_FAILED -> unHoldRequest(event);
            case SELL_ITEM_SUCCESSFULLY -> processed(event);
        }
    }

    private void holdItemFailed(EventConductorDto event) {
        transferService.update(event.getTransferId(), UpdateTransferDto.builder()
                .amount(event.getAmount())
                .description(event.getDescription())
                .status(TransferStatus.HOLD_ITEM_FAILED)
                .build());
        sendNotificationEvent(event.getSenderUserId(), PURCHASE_FAILED);
    }

    private void transferAmount(EventConductorDto event) {
        transferService.update(event.getTransferId(), UpdateTransferDto.builder()
                        .amount(event.getAmount())
                        .receiverUserId(event.getReceiverUserId())
                        .status(TransferStatus.ITEM_HOLD_SUCCESSFULLY)
                .build());
        sendConductorEvent(event, EventConductorType.TRANSFER_AMOUNT_REQUEST);
    }

    private void sellItem(EventConductorDto event) {
        transferService.updateStatus(event.getTransferId(), TransferStatus.TRANSFER_AMOUNT_SUCCESSFULLY);
        sendConductorEvent(event, EventConductorType.SELL_ITEM_REQUEST);
    }

    private void unHoldRequest(EventConductorDto event) {
        transferService.update(event.getTransferId(), UpdateTransferDto.builder()
                .amount(event.getAmount())
                .description(event.getDescription())
                .status(TransferStatus.TRANSFER_AMOUNT_FAILED)
                .build());
        sendConductorEvent(event, EventConductorType.UN_HOLD_ITEM_REQUEST);
        sendNotificationEvent(event.getSenderUserId(), PURCHASE_FAILED);
    }

    @SneakyThrows
    private void processed(EventConductorDto event) {
        transferService.updateStatus(event.getTransferId(), TransferStatus.PROCESSED);
        sendNotificationEvent(event.getSenderUserId(), PURCHASE_SUCCESSFUL);
    }

    @SneakyThrows
    private void sendConductorEvent(EventConductorDto event, EventConductorType type) {
        event.setType(type);
        kafkaTemplate.send(conductorTopic, objectMapper.writeValueAsString(event));
    }

    @SneakyThrows
    private void sendNotificationEvent(Integer userId, EventNotificationType type) {
        EventNotificationDto event = EventNotificationDto.builder()
                .userId(userId)
                .type(type)
                .build();
        kafkaTemplate.send(notificationTopic, objectMapper.writeValueAsString(event));
    }
}
