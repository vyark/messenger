package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.extensions.RunnerExtension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RunnerExtension.class)
public class TemplateEngineTest {

    private static final String TXT_FILE_PATH = "template.txt";
    private static final String HTML_FILE_PATH = "template.html";

    private TemplateEngine templateEngine = new TemplateEngine();
    private BufferedReader bufferedReader;

    @Test
    public void generateMessageFromTxtTemplate_shouldSucceed() throws Exception {
        bufferedReader = new BufferedReader(new FileReader(TXT_FILE_PATH));
        String payload = bufferedReader.lines().collect(Collectors.joining());

        Template template = new Template(payload);
        Client client = new Client();
        client.setAddresses("mail@mail.com");

        String result = templateEngine.generateMessage(template, client);

        assertEquals(result, payload.replace("\\#\\{recipientName\\}", client.getAddresses()));
    }

    @Test
    public void generateMessageFromHtmlTemplate_shouldSucceed() throws Exception {
        bufferedReader = new BufferedReader(new FileReader(HTML_FILE_PATH));
        String payload = bufferedReader.lines().collect(Collectors.joining());

        Template template = new Template(payload);
        Client client = new Client();
        client.setAddresses("mail@mail.com");

        String result = templateEngine.generateMessage(template, client);

        assertEquals(result, payload.replace("\\#\\{recipientName\\}", client.getAddresses()));
    }

    @Test
    @Tag("UnitTest")
    public void generateMessage_shouldThrowException() {
        Template template = new Template("");
        Client client = new Client();
        client.setAddresses("mailmail.com");

        Exception exception = assertThrows(Exception.class, () -> {
            templateEngine.generateMessage(template, client);
        });

        String expectedMessage = "Email address is not valid: mailmail.com";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @EnabledOnJre({JRE.JAVA_15})
    @DisabledOnOs(OS.LINUX)
    public void parameterizedTest() throws Exception {
        String payload = "To: #{recipientName}\n" + "From: #{senderName}\n" + "Subject: " +
                "#{subject}\n" +
                "\n" + "Dear, #{recipientName}!\n" + "\n" + "This is an email template!\n" + "\n" + "Kind regards,\n" + "#{senderName}\n";

        Template template = new Template(payload);
        Client client = new Client();
        client.setAddresses("mail@mail.com");

        String result = templateEngine.generateMessage(template, client);

        assertEquals(result, payload.replace("\\#\\{recipientName\\}", client.getAddresses()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"olga@mail.com", "john@mail.com", "mary@mail.com"})
    void generateMessage_parameterizedTest(String name) throws Exception {
        String payload = "To: #{recipientName}";

        Template template = new Template(payload);
        Client client = new Client();
        client.setAddresses(name);

        String result = templateEngine.generateMessage(template, client);

        assertEquals(result, payload.replace("\\#\\{recipientName\\}", client.getAddresses()));
    }

    @Test
    void givenTestMethodWithTempDirectory_whenWriteToFile_thenContentIsCorrect(@TempDir Path tempDir)
            throws IOException {
        Path strings = tempDir.resolve("output.txt");

        List<String> lines = Arrays.asList("Olga", "John", "Mary");
        Files.write(strings, lines);

        assertAll(
                () -> assertTrue(Files.exists(strings)),
                () -> assertLinesMatch(lines, Files.readAllLines(strings)));
    }
}
