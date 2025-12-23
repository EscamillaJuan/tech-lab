package com.escamilla.behavioral.strategy;

public class StrategyMain {
    public static void main(String[] args) {
        DiscountStrategy strategy = new PremiumDiscountt();
        PriceCalculator calculator = new PriceCalculator(strategy);
        double finalPrice = calculator.calculateFinalPrice(100.0);

        System.out.println(finalPrice);
    }
}
