package com.codeprep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PayPal Strategy Tests")
class PayPalTest {

    private PayPal payPal;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        payPal = new PayPal();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should process payment with correct amount")
    void testProcessPayment() {
        double amount = 500.00;
        payPal.processPayment(amount);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing PayPal Payment of $500.0"));
        assertTrue(output.contains("Redirecting to PayPal"));
        assertTrue(output.contains("Authenticating user"));
        assertTrue(output.contains("Payment successful"));
    }

    @Test
    @DisplayName("Should handle decimal amounts")
    void testProcessPaymentWithDecimal() {
        payPal.processPayment(99.99);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing PayPal Payment of $99.99"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

