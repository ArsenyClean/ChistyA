package ru.job4j.currency.thread;

public class CountChar implements Runnable {

    private String string;

    CountChar(String string) {
        this.string = string;
    }

    private void doing() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            char[] chars = string.toCharArray();
            int counter = 0;
            for (int i = 0; i < chars.length; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                counter++;
            }
            System.out.println("Количество символов: " + counter);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            doing();
        } catch (InterruptedException e) {
            return;
        }
    }
}