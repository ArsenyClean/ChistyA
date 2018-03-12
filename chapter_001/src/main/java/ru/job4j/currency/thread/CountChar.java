package ru.job4j.currency.thread;

public class CountChar implements Runnable {

    private String string;

    CountChar(String string) {
        this.string = string;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                char[] chars = string.toCharArray();
                int counter = 0;
                for (int i = 0; i < chars.length; i++) {
                    counter++;
                }
                System.out.println("Количество символов: " + counter);
                Thread.currentThread().interrupt();
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}