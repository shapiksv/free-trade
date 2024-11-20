package com.trade.free.notification_adapter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Data
@Configuration
@ConfigurationProperties(prefix = "gmail-configuration")
public class GmailConfiguration {

    private final static String AUTH_KEY = "mail.smtp.auth";
    private final static String STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";
    private final static String HOST_KEY = "mail.smtp.host";
    private final static String PORT_KEY = "mail.smtp.port";
    private final static String TRUST_KEY = "mail.smtp.ssl.trust";

    private String host;
    private String port;
    private String username;
    private String password;
    private String from;
    private String to;


    @Bean
    public Session session() {

        Properties prop = new Properties();
        prop.put(AUTH_KEY, true);
        prop.put(STARTTLS_ENABLE_KEY, "true");
        prop.put(HOST_KEY, host);
        prop.put(PORT_KEY, port);
        prop.put(TRUST_KEY, host);

        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
