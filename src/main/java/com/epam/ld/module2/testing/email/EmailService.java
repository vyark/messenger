package com.epam.ld.module2.testing.email;

public interface EmailService {
    boolean sendSimpleMessage(String to,
                           String subject,
                           String text);
    boolean sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        String templateModel);
}
