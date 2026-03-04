package com.codeprep.service.impl;

import com.codeprep.service.IPaymentService;

public class PaymentService implements IPaymentService {

    @Override
    public boolean processPayment(String userName) {
        System.out.println("Payment processed for user: " + userName);
        return true;
    }
}
