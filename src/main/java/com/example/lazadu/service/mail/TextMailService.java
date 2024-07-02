package com.example.lazadu.service.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class TextMailService {

    private final JavaMailSender mailSender;

    public void send(TextMailInfo mailInfo) {

        SimpleMailMessage message = new SimpleMailMessage();

        if (StringUtils.isNotBlank(mailInfo.getFrom())) {
            message.setFrom(mailInfo.getFrom());
        }

        if (StringUtils.isNotBlank(mailInfo.getReplyTo())) {
            message.setReplyTo(mailInfo.getReplyTo());
        }

        if (Objects.nonNull(mailInfo.getTo())
                && ArrayUtils.isNotEmpty(mailInfo.getTo())) {
            message.setTo(mailInfo.getTo());
        }

        if (Objects.nonNull(mailInfo.getCc())
                && ArrayUtils.isNotEmpty(mailInfo.getCc())) {
            message.setCc(mailInfo.getCc());
        }

        if (Objects.nonNull(mailInfo.getBcc())
                && ArrayUtils.isNotEmpty(mailInfo.getBcc())) {
            message.setBcc(mailInfo.getBcc());
        }

        if (Objects.nonNull(mailInfo.getSentDate())) {
            message.setSentDate(mailInfo.getSentDate());
        }

        if (StringUtils.isNotBlank(mailInfo.getSubject())) {
            message.setSubject(mailInfo.getSubject());
        }

        if (StringUtils.isNotBlank(mailInfo.getText())) {
            message.setText(mailInfo.getText());
        }

        try {
            mailSender.send(message);
        } catch (MailParseException | MailAuthenticationException | MailSendException e) {
            log.error(e.getMessage());
        } finally {
            log.info("Send email finished");
        }
    }
}
