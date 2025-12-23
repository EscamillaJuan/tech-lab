package com.escamilla.creational.builder;

import java.util.List;

public class Client {
    private final int id;
    private final String name;
    private final List<String> emails;

    private Client(int id, String name, List<String> emails) {
        this.id = id;
        this.name = name;
        this.emails = emails;
    }

    public List<String> getEmails() {
        return emails;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static class Builder {
        private int id;
        private String name;
        private List<String> emails;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder emails(List<String> emails) {
            this.emails = emails;
            return this;
        }

        public Client build() {
            return new Client(this.id, this.name, this.emails);
        }
    }
}

