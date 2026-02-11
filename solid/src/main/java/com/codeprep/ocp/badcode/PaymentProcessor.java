package com.codeprep.ocp.badcode;

/**
 * BAD CODE - Violates Open/Closed Principle (OCP)
 *
 * OCP states: "Software entities should be open for extension but closed for modification"
 *
 * Violation:
 * - Every time we need to add a new payment method (e.g., GooglePay, ApplePay, Cryptocurrency),
 *   we must MODIFY this existing class by adding new if-else conditions.
 * - This makes the code rigid and increases the risk of breaking existing functionality.
 * - The class is not closed for modification.
 *
 * Better Approach:
 * - Use polymorphism with a PaymentMethod interface/abstract class
 * - Each payment type (CreditCard, DebitCard, PayPal) should be a separate class
 * - New payment methods can be added by creating new classes without modifying existing code
 */
public class PaymentProcessor {

    public void processPayment(String paymentMethod, Double amount) {
        System.out.println("Processing Payment...");
        if (paymentMethod.equals("CreditCard")) {
            System.out.println("Credit Card Payment...");
        } else if (paymentMethod.equals("DebitCard")) {
            System.out.println("Debit Card Payment...");
        }  else if (paymentMethod.equals("PayPal")) {
            System.out.println("PayPal Payment...");
        }else {
            System.out.println("Invalid Payment Method");
            throw new IllegalArgumentException("Invalid Payment Method");
        }
    }
}
