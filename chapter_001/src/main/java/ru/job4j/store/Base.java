package ru.job4j.store;

public abstract class Base {
    private final String id;

    Base(final String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}