package ru.job4j.currency.thread.model;

public class Model  {

    Integer integer;

    Model(Integer integer) {
        this.integer = integer;
    }

    public synchronized Integer getter() {
        return  integer;
    }

    public synchronized void increment() {
        integer++;
    }

    public synchronized void decrement() {
        integer--;
    }
}