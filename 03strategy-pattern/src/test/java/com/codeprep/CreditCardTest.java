package com.codeprep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreditCard Strategy Tests")
class CreditCardTest {

    private CreditCard creditCard;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        creditCard = new CreditCard();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should process payment with correct amount")
    void testProcessPayment() {
        double amount = 100.50;
        creditCard.processPayment(amount);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing Credit Card Payment of $100.5"));
        assertTrue(output.contains("Validating credit card details"));
        assertTrue(output.contains("Checking credit limit"));
        assertTrue(output.contains("Payment successful"));
    }

    @Test
    @DisplayName("Should handle zero amount")
    void testProcessPaymentWithZeroAmount() {
        creditCard.processPayment(0.0);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing Credit Card Payment of $0.0"));
    }

    @Test
    @DisplayName("Should handle large amount")
    void testProcessPaymentWithLargeAmount() {
        creditCard.processPayment(999999.99);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing Credit Card Payment of $999999.99"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

