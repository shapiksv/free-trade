package com.trade.free.notification_adapter.service.impl;

import com.trade.free.dto.rest.notification.NotificationDtoReq;
import com.trade.free.notification_adapter.config.GmailConfiguration;
import com.trade.free.notification_adapter.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final static String TEXT_TYPE = "text/html; charset=utf-8";

   private final GmailConfiguration gmailConfiguration;

   private final Session session;

    @Override
    @SneakyThrows
    public void send(NotificationDtoReq req) {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(gmailConfiguration.getFrom()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(gmailConfiguration.getTo() != null ?
                        gmailConfiguration.getTo() : req.getContact()));
        message.setSubject(req.getSubject());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(req.getText(), TEXT_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        log.info("Mail to; [{}] sent successfully", req.getContact());
    }
}
