package com.example.lazadu.service.mail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class HtmlMailInfo extends MailInfo {

    @NonNull
    private String templateName;
    private Map<String, Object> data;
}
