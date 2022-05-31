package com.epam.ld.module2.testing.email;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "noreply@epam.com";

    private final JavaMailSender emailSender;

    private final SimpleMailMessage template;

    public EmailServiceImpl(JavaMailSender emailSender,
                            SimpleMailMessage ololo) {
        this.emailSender = emailSender;
        this.template = ololo;
    }

    public boolean sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
            return true;
        } catch (MailException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String templateModel) {
        String text = String.format(template.getText(), templateModel);
        sendSimpleMessage(to, subject, text);
        return true;
    }
}