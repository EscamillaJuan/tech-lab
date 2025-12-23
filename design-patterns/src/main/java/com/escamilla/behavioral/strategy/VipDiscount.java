package com.escamilla.behavioral.strategy;

public class VipDiscount implements DiscountStrategy {
    @Override
    public double appy(double price) {
        return price * 0.75;
    }
}
