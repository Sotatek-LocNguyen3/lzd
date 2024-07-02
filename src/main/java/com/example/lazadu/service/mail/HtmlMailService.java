package com.example.lazadu.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class HtmlMailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    public void send(HtmlMailInfo mailInfo) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, mailInfo.isMultipart(), StandardCharsets.UTF_8.name());
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

            final Context context = new Context();
            context.setLocale(Locale.ENGLISH);
            context.setVariables(mailInfo.getData());

            final String content = templateEngine.process(mailInfo.getTemplateName(), context);
            message.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MailAuthenticationException | MailSendException | MessagingException e) {
            log.error(e.getMessage());
        } finally {
            log.info("Send email finished");
        }

    }
}
