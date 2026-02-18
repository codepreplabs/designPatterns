package com.codeprep;

/**
 * Concrete strategy for processing credit card payments.
 */
public class CreditCard implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card Payment of $" + amount);
        System.out.println("  → Validating credit card details...");
        System.out.println("  → Checking credit limit...");
        System.out.println("  → Payment successful!");
    }
}
