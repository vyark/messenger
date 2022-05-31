package com.epam.ld.module2.testing.email;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidationTest {

    @Test
    public void testGmailSpecialCase() {
        String emailAddress = "username+something@domain.com";

        assertTrue(EmailValidation.patternMatches(emailAddress));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsWithCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Valid email",
                        () -> assertTrue(EmailValidation.patternMatches("olga@mail.com"))),
                DynamicTest.dynamicTest("Non valid email",
                        () -> assertFalse(EmailValidation.patternMatches("olga"))));
    }
}
