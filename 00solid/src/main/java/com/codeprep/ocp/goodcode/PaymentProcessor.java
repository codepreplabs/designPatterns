package com.codeprep.ocp.goodcode;

public class PaymentProcessor {

    void processPayment(PaymentMethod paymentMethod, Double amount) {
        paymentMethod.pay(amount);
    }
}
