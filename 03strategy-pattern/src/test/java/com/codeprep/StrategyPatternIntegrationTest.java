package com.codeprep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Strategy Pattern Integration Tests")
class StrategyPatternIntegrationTest {

    @Test
    @DisplayName("Should demonstrate complete strategy pattern workflow")
    void testCompleteWorkflow() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Create payment service with credit card strategy
            PaymentStrategy creditCard = new CreditCard();
            PaymentService service = new PaymentService(creditCard);

            // Process first payment
            service.processPayment(150.75);
            assertTrue(outputStream.toString().contains("Credit Card Payment"));

            outputStream.reset();

            // Switch to debit card strategy
            PaymentStrategy debitCard = new DebitCard();
            service.setStrategy(debitCard);
            service.processPayment(89.99);
            assertTrue(outputStream.toString().contains("Debit Card Payment"));

            outputStream.reset();

            // Switch to PayPal strategy
            PaymentStrategy payPal = new PayPal();
            service.setStrategy(payPal);
            service.processPayment(250.00);
            assertTrue(outputStream.toString().contains("PayPal Payment"));

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Should handle different amounts across strategies")
    void testDifferentAmountsAcrossStrategies() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            PaymentService service = new PaymentService(new CreditCard());

            service.processPayment(10.99);
            service.processPayment(1000.00);
            service.processPayment(0.01);

            String output = outputStream.toString();
            assertTrue(output.contains("$10.99"));
            assertTrue(output.contains("$1000.0"));
            assertTrue(output.contains("$0.01"));

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Should maintain independence between different service instances")
    void testServiceInstanceIndependence() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            PaymentService service1 = new PaymentService(new CreditCard());
            PaymentService service2 = new PaymentService(new DebitCard());
            PaymentService service3 = new PaymentService(new PayPal());

            // Change strategy of service1
            service1.setStrategy(new PayPal());

            // Service2 and service3 should remain unchanged
            outputStream.reset();
            service2.processPayment(100.0);
            assertTrue(outputStream.toString().contains("Debit Card Payment"));

            outputStream.reset();
            service3.processPayment(100.0);
            assertTrue(outputStream.toString().contains("PayPal Payment"));

        } finally {
            System.setOut(originalOut);
        }
    }
}

