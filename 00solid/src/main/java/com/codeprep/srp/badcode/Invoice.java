package com.codeprep.srp.badcode;

public class Invoice {

    private Double amount;

    public Invoice(Double amount) {
        this.amount = amount;
    }

    public void generateInvoice() {
        System.out.println("Generating Invoice...");
    }

    public void saveToDatabase() {
        System.out.println("Saving Invoice...");
    }

    public void sendNotification() {
        System.out.println("Sending notification for Invoice...");
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
