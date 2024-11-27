package com.trade.free.notification_adapter.service.impl;

import com.trade.free.dto.rest.notification.NotificationDtoReq;
import com.trade.free.notification_adapter.config.GmailConfiguration;
import com.trade.free.notification_adapter.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final GmailConfiguration gmailConfiguration;


    @Override
    public void send(NotificationDtoReq req) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(gmailConfiguration.getFrom());
        message.setTo(gmailConfiguration.getTo() != null ?
                gmailConfiguration.getTo() : req.getContact());
        message.setText(req.getText());
        message.setSubject(req.getSubject());
        mailSender.send(message);
    }
}
