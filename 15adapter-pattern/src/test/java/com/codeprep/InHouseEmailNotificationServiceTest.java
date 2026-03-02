package com.codeprep;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InHouseEmailNotificationServiceTest {

    private final InHouseEmailNotificationService service = new InHouseEmailNotificationService();

    @Test
    void send_shouldPrintToEmail() {
        ByteArrayOutputStream out = captureOutput();

        service.send("alice@example.com", "Hi", "Hello!");

        assertTrue(out.toString().contains("alice@example.com"));
    }

    @Test
    void send_shouldPrintSubject() {
        ByteArrayOutputStream out = captureOutput();

        service.send("alice@example.com", "Welcome", "Hello!");

        assertTrue(out.toString().contains("Welcome"));
    }

    @Test
    void send_shouldPrintBody() {
        ByteArrayOutputStream out = captureOutput();

        service.send("alice@example.com", "Hi", "Hello body!");

        assertTrue(out.toString().contains("Hello body!"));
    }

    private ByteArrayOutputStream captureOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }
}


