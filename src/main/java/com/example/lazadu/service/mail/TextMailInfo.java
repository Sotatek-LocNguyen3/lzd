package com.example.lazadu.service.mail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class TextMailInfo extends MailInfo {

    private String text;
}
