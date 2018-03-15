package ru.job4j.currency.thread;

public class Time implements Runnable {

    long finishTime;
    String string;

    public Time(int finishTime, String string) {
        this.finishTime = finishTime;
        this.string = string;
    }

    private void counter() {
        try {
            Thread.sleep(finishTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Время выполнения кончилось");
    }

    @Override
    public void run() {
        counter();
    }
}