package com.codeprep.ocp.goodcode;

public class DebitCard implements PaymentMethod {

    @Override
    public String getType() {
        return "DebitCard";
    }

    @Override
    public void pay(Double amount) {
        System.out.println("DebitCard Pay" + amount);
    }
}
