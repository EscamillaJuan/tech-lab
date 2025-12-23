package com.escamilla;

import com.escamilla.creational.builder.Client;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Client client = new Client
                .Builder()
                .id(1)
                .name("Juan")
                .emails(List.of("juan@correo.com"))
                .build();
        System.out.println(client.getName());
    }
}