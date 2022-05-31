package com.epam.ld.module2.testing;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.epam", "com.epam.ld.module2.testing"})
public class SpringBootConsoleApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }
}