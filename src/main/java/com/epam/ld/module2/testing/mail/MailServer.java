package com.epam.ld.module2.testing.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Mail server class.
 */
public class MailServer {
    private static final String NOREPLY_ADDRESS = "noreply@mail.com";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage template;

    /**
     * Send notification.
     *
     * @param addresses      the addresses
     * @param subject      the subject
     * @param messageContent the message content
     */
    public void send(String addresses, String subject, String messageContent) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(NOREPLY_ADDRESS);
        message.setTo(addresses);
        message.setSubject(subject);
        message.setText(messageContent);

        emailSender.send(message);
    }
}
