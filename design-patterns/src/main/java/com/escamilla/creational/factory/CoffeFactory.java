package com.escamilla.creational.factory;

public class CoffeFactory {
    public static Coffee createCoffee(String type) {
        if (type.equalsIgnoreCase("espresso")) {
            return new Espresso();
        } else if (type.equalsIgnoreCase("latte")) {
            return new Latte();
        }
        throw new IllegalArgumentException("Unknown coffee type: " + type);
    }
}
