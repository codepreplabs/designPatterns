package com.codeprep.ocp.goodcode;

public class CreditCard implements PaymentMethod {

    @Override
    public String getType() {
        return "CreditCard";
    }

    @Override
    public void pay(Double amount) {
        System.out.println("CreditCard Pay" + amount);
    }
}
