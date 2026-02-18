package com.codeprep;

/**
 * Concrete strategy for processing PayPal payments.
 */
public class PayPal implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal Payment of $" + amount);
        System.out.println("  → Redirecting to PayPal...");
        System.out.println("  → Authenticating user...");
        System.out.println("  → Payment successful!");
    }
}

