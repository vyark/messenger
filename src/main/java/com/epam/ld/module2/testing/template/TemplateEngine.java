package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.email.EmailValidation;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) throws Exception {
        if (EmailValidation.patternMatches(client.getAddresses())) {
            String formattedPayload = template.getPayload().replace("\\#\\{recipientName}", "\\%s");
            return String.format(formattedPayload, client.getAddresses());
        }
        throw new Exception("Email address is not valid: " + client.getAddresses());
    }
}
