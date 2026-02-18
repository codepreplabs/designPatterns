package com.codeprep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DebitCard Strategy Tests")
class DebitCardTest {

    private DebitCard debitCard;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        debitCard = new DebitCard();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should process payment with correct amount")
    void testProcessPayment() {
        double amount = 250.75;
        debitCard.processPayment(amount);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing Debit Card Payment of $250.75"));
        assertTrue(output.contains("Validating debit card details"));
        assertTrue(output.contains("Checking account balance"));
        assertTrue(output.contains("Payment successful"));
    }

    @Test
    @DisplayName("Should handle small amount")
    void testProcessPaymentWithSmallAmount() {
        debitCard.processPayment(1.99);

        String output = outputStream.toString();
        assertTrue(output.contains("Processing Debit Card Payment of $1.99"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

