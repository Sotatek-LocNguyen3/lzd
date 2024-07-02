package com.example.lazadu.service.mail;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
public class MailInfo {

    @Nullable
    private String from;

    @Nullable
    private String replyTo;

    @Nullable
    private String[] to;

    @Nullable
    private String[] cc;

    @Nullable
    private String[] bcc;

    @Nullable
    private Date sentDate;

    @Nullable
    private String subject;

    private boolean isMultipart;

}
