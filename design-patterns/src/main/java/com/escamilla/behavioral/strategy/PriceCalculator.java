package com.escamilla.behavioral.strategy;

public class PriceCalculator {
    private DiscountStrategy discountStrategy;

    public PriceCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateFinalPrice(double price) {
        return discountStrategy.appy(price);
    }
}
