package com.epam.ld.module2.testing.email;

import java.util.regex.Pattern;

/**
 * Email validation class.
 */
public class EmailValidation {

    public static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
            + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
    /**
     * Send notification.
     *
     * @param emailAddress  the email address
    */
    public static boolean patternMatches(String emailAddress) {
        return Pattern.compile(REGEX_PATTERN)
                      .matcher(emailAddress)
                      .matches();
    }
}
