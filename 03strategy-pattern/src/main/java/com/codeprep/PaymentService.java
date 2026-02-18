package com.codeprep;

/**
 * Context class that uses a payment strategy.
 * Allows dynamic switching of payment strategies at runtime.
 */
public class PaymentService {

    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Allows changing the payment strategy at runtime.
     * @param strategy the new payment strategy to use
     */
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Process payment using the current strategy.
     * @param amount the amount to be processed
     */
    public void processPayment(double amount) {
        strategy.processPayment(amount);
    }
}
