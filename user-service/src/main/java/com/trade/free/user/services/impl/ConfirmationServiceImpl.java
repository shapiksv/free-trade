package com.trade.free.user.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.free.dto.enums.EventNotificationType;
import com.trade.free.dto.enums.EventUserType;
import com.trade.free.dto.event.EventNotificationDto;
import com.trade.free.dto.event.EventUserDto;
import com.trade.free.user.entities.ConfirmationCode;
import com.trade.free.user.entities.User;
import com.trade.free.user.enums.ConfirmationStatus;
import com.trade.free.user.exception.OtpNotFoundException;
import com.trade.free.user.repository.ConfirmationCodeRepository;
import com.trade.free.user.services.ConfirmationService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfirmationServiceImpl implements ConfirmationService {

    private final static Integer LENGTH = 4;
    private final static Boolean USE_LETTERS = false;
    private final static Boolean USE_NUMBERS = true;

    private final String topic;
    private final ConfirmationCodeRepository repository;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ConfirmationServiceImpl(@Value("${topic.notification}") String topic,
                                   ConfirmationCodeRepository repository,
                                   KafkaTemplate<String, String> kafkaTemplate,
                                   ObjectMapper objectMapper) {
        this.topic = topic;
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public void createOtp(String email) {

        var confirmationCode = repository
                .findByStatusAndEmail(ConfirmationStatus.NEW, email)
                .orElseGet(() -> ConfirmationCode.create(email));

        confirmationCode.setCode(RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS));
        confirmationCode = repository.save(confirmationCode);

        kafkaTemplate.send(topic, objectMapper.writeValueAsString(EventNotificationDto.builder()
                .contact(email)
                .code(confirmationCode.getCode())
                .type(EventNotificationType.SEND_OTP)
                .build()));

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean checkOtp(String otp, String email) {
        var confirmationCode = repository.findByStatusAndEmail(ConfirmationStatus.NEW, email)
                .orElseThrow(OtpNotFoundException::new);
        boolean isValid;
        if (confirmationCode.getCode().equals(otp)) {
            confirmationCode.setStatus(ConfirmationStatus.CONFIRMED);
            isValid = true;
        } else {
            confirmationCode.increaseAttempt();
            isValid = false;
        }
        repository.save(confirmationCode);
        return isValid;
    }

    @SneakyThrows
    private void sendEvent(User user, EventUserType type) {
        EventUserDto event = new EventUserDto(user.getId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail(),
                type);
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(event));
    }
}
