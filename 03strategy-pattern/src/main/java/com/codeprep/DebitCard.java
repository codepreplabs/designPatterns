package com.codeprep;

/**
 * Concrete strategy for processing debit card payments.
 */
public class DebitCard implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Debit Card Payment of $" + amount);
        System.out.println("  → Validating debit card details...");
        System.out.println("  → Checking account balance...");
        System.out.println("  → Payment successful!");
    }
}
