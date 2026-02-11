package com.codeprep.ocp.goodcode;

public class DemoOpenClosedPrinciple {

    public static void main(String[] args) {

        PaymentProcessor paymentProcessor = new PaymentProcessor();
        PaymentMethod paymentMethod1 = new DebitCard();
        paymentProcessor.processPayment(paymentMethod1, 100.00);

        PaymentMethod paymentMethod2 = new CreditCard();
        paymentProcessor.processPayment(paymentMethod2, 100.00);
    }
}
