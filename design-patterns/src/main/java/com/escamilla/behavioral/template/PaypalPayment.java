package com.escamilla.behavioral.template;

public class PaypalPayment extends PaymentProcessor {
    @Override
    protected void calculateFee(double amount) {
        System.out.println("Calculating PayPal fee");
    }

    @Override
    protected void executePayment(double amount) {
        System.out.println("Processing PayPal payment: " + amount);
    }
}
