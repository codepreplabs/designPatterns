package com.codeprep;

/**
 * Strategy interface defining the contract for payment processing strategies.
 * Each concrete strategy implements different payment methods.
 */
public interface PaymentStrategy {

    /**
     * Process payment using the specific payment method.
     * @param amount the amount to be processed
     */
    void processPayment(double amount);
}
