package ru.job4j.currency.thread;

public class FirstMessageLastMessage {

    public static class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Поток начал работу ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поток завершил работу ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Thread1());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Программа завершила работу");
    }
}