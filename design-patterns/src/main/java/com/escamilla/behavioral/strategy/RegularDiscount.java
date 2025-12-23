package com.escamilla.behavioral.strategy;

public class RegularDiscount implements DiscountStrategy {
    @Override
    public double appy(double price) {
        return price * 0.95;
    }
}
