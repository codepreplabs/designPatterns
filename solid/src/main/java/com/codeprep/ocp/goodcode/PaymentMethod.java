package com.codeprep.ocp.goodcode;

public interface PaymentMethod {
    String getType();
    void pay(Double amount);
}
