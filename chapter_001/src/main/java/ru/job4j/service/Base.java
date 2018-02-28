package ru.job4j.service;

public abstract class Base {

    private final String id;

    Base(final String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}