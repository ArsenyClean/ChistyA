package ru.job4j.currency.thread.model;

public class ModelThread implements Runnable {

    Model integer;

    ModelThread(Model model) {
        integer = model;
    }

    @Override
    public void run() {
        integer.decrement();
        System.out.println(integer.getter());
        System.out.println();
        System.out.println(integer.getter());
        System.out.println();
        System.out.println(integer.getter());
        System.out.println(integer.getter());
        System.out.println();
        System.out.println(integer.getter());
        System.out.println();
        System.out.println(integer.getter());
        System.out.println();
        integer.increment();
        System.out.println(integer.getter());
        System.out.println();
    }
}