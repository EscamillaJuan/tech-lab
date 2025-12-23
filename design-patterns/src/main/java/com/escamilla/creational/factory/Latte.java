package com.escamilla.creational.factory;

public class Latte implements Coffee{
    @Override
    public void prepare() {
        System.out.println("Latte in progress");
    }
}
