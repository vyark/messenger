package com.epam.ld.module2.testing.mail;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Mail {
    @Email
    @NotNull
    @Size(min = 1, message = "Please, set an email address to send the message to it")
    private String to;
    private String recipientName;
    private String subject;
    private String text;
    private String senderName;
    private String templateEngine;
}