package com.escamilla.behavioral.template;

public class CreditCardPayment extends PaymentProcessor{
    @Override
    protected void calculateFee(double amount) {
        System.out.println("Calculatin credit card fee");
    }

    @Override
    protected void executePayment(double amount) {
        System.out.println("Charging credit card: " + amount);
    }
}
