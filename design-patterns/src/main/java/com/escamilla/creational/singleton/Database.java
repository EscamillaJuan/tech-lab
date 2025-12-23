package com.escamilla.creational.singleton;

import java.util.Objects;

public final class Database {
  private static Database database;

  private Database() {}

  public static Database getInstance() {
    if (database == null) {
      database = new Database();
      database.connect("/usr/local/data/user.db");
    }
    return database;
  }

  private void connect(String url) { Objects.requireNonNull(url); }

  public static void main(String[] args) {
    Database a = Database.getInstance();
    Database b = Database.getInstance();

    System.out.println(a == b);
  }
}
