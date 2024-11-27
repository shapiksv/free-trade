package com.trade.free.notification.config;

import com.trade.free.dto.enums.EventNotificationType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "message")
public class MessageParams {

    private Map<EventNotificationType, String> subjectMessage;
    private Map<EventNotificationType, String> textMessage;
}
