package com.escamilla.creational.factory;

public class Espresso implements Coffee {
    @Override
    public void prepare() {
        System.out.println("Espresso in progress");
    }
}
