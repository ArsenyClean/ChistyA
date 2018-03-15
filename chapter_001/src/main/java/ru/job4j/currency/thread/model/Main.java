package ru.job4j.currency.thread.model;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Model model = new Model(5);

        Thread thread1 = new Thread(new ModelThread(model));
        Thread thread2 = new Thread(new ModelThread(model));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}