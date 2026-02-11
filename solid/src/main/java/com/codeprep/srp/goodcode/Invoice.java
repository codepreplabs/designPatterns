package com.codeprep.srp.goodcode;

public class Invoice {

    private Double amount;

    public Invoice(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void generateInvoice() {
        System.out.println("Generating Invoice...");
    }
}
