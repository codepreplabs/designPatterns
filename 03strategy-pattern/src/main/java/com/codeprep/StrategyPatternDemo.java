package com.codeprep;

public class StrategyPatternDemo {

    static void main(String[] args) {
        System.out.println("=== Strategy Pattern Demo ===\n");

        // Using Credit Card strategy
        PaymentStrategy creditCard = new CreditCard();
        PaymentService paymentService = new PaymentService(creditCard);
        System.out.println("Processing payment with Credit Card:");
        paymentService.processPayment(150.75);

        System.out.println("\n--- Switching Strategy at Runtime ---\n");

        // Switching to Debit Card strategy at runtime
        PaymentStrategy debitCard = new DebitCard();
        paymentService.setStrategy(debitCard);
        System.out.println("Processing payment with Debit Card:");
        paymentService.processPayment(89.99);

        System.out.println("\n--- Creating new service with different strategy ---\n");

        // Creating new service with Debit Card
        PaymentService anotherService = new PaymentService(debitCard);
        System.out.println("Processing another payment with Debit Card:");
        anotherService.processPayment(250.00);
    }
}
