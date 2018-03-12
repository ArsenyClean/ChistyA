package ru.job4j.currency.thread;

public class Main {

    public static void main(String[] args) {
        String string = "abc defg hig klmnop qrstuvwxyz";
        Thread thread1 = new Thread(new Time(1000, string));
        Thread thread2 = new Thread(new CountChar(string));
        thread1.start();
        thread2.start();
        while (!thread1.isInterrupted()) {
        }
        thread2.interrupt();
    }
}