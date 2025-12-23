package com.escamilla.behavioral.template;

public class TemplateMain {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaypalPayment();
        processor.process(100.0);

        System.out.println("----------------");

        processor = new CreditCardPayment();
        processor.process(100.0);
    }
}
