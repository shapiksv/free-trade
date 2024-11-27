package com.trade.free.notification_adapter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "gmail-configuration")
public class GmailConfiguration {
    private String from;
    private String to;
}
