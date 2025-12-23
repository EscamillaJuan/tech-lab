package com.escamilla.behavioral.template;

public abstract class PaymentProcessor {
    //Template method (final = cannot be changed)
    public final void process(double amount) {
        validate();
        calculateFee(amount);
        executePayment(amount);
        sendConfirmation();
    }

    protected void validate() {
        System.out.println("Validating payment data...");
    }

    protected abstract void calculateFee(double amount);

    protected abstract void executePayment(double amount);

    protected void sendConfirmation() {
        System.out.println("Sending confirmation email...");
    }
}
