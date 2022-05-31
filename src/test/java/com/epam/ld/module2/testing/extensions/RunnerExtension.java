package com.epam.ld.module2.testing.extensions;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * Custom RunnerExtension to output test execution information to file
 * */
public class RunnerExtension implements AfterTestExecutionCallback {

    @SneakyThrows
    @Override
    public void afterTestExecution(ExtensionContext context) throws IOException {
        Boolean testResult = context.getExecutionException().isPresent();

        BufferedWriter writer = new BufferedWriter(new FileWriter("testResult.txt"));
        writer.write(String.valueOf(testResult));

        writer.close();
    }
}