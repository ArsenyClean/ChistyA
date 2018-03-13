package ru.job4j.currency.thread.userstorage;

public class User {

    private final int id;
    private int amount;

    public User(final int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}