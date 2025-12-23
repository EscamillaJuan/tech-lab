package com.escamilla.behavioral.strategy;

public class PremiumDiscountt implements DiscountStrategy {
    @Override
    public double appy(double price) {
        return price * 0.85;
    }
}
