package com.codeprep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PaymentService Context Tests")
class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentStrategy mockStrategy;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should initialize with a strategy")
    void testInitializationWithStrategy() {
        PaymentStrategy strategy = new CreditCard();
        paymentService = new PaymentService(strategy);
        assertNotNull(paymentService);
    }

    @Test
    @DisplayName("Should process payment using initial strategy")
    void testProcessPaymentWithInitialStrategy() {
        paymentService = new PaymentService(new CreditCard());
        paymentService.processPayment(100.0);

        String output = outputStream.toString();
        assertTrue(output.contains("Credit Card Payment"));
    }

    @Test
    @DisplayName("Should switch strategy at runtime")
    void testSwitchStrategy() {
        paymentService = new PaymentService(new CreditCard());
        paymentService.processPayment(100.0);

        String output1 = outputStream.toString();
        assertTrue(output1.contains("Credit Card Payment"));

        // Clear output stream
        outputStream.reset();

        // Switch strategy
        paymentService.setStrategy(new DebitCard());
        paymentService.processPayment(200.0);

        String output2 = outputStream.toString();
        assertTrue(output2.contains("Debit Card Payment"));
        assertFalse(output2.contains("Credit Card Payment"));
    }

    @Test
    @DisplayName("Should process multiple payments with different strategies")
    void testMultiplePaymentsWithDifferentStrategies() {
        paymentService = new PaymentService(new CreditCard());
        paymentService.processPayment(50.0);

        outputStream.reset();
        paymentService.setStrategy(new DebitCard());
        paymentService.processPayment(75.0);

        outputStream.reset();
        paymentService.setStrategy(new PayPal());
        paymentService.processPayment(100.0);

        String output = outputStream.toString();
        assertTrue(output.contains("PayPal Payment"));
    }

    @Test
    @DisplayName("Should allow creating multiple service instances with different strategies")
    void testMultipleServiceInstances() {
        PaymentService service1 = new PaymentService(new CreditCard());
        PaymentService service2 = new PaymentService(new DebitCard());

        service1.processPayment(100.0);
        String output1 = outputStream.toString();
        assertTrue(output1.contains("Credit Card Payment"));

        outputStream.reset();

        service2.processPayment(200.0);
        String output2 = outputStream.toString();
        assertTrue(output2.contains("Debit Card Payment"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

