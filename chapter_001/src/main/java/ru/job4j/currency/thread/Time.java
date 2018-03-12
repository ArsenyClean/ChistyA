package ru.job4j.currency.thread;

public class Time implements Runnable {

    long finishTime;
    String string;

    public Time(int finishTime, String string) {
        this.finishTime = finishTime;
        this.string = string;
    }

    @Override
    public void run() {
        long flag = 0;
        long currentT = System.currentTimeMillis();
        while (flag < finishTime) {
            flag = System.currentTimeMillis() - currentT;
        }
        System.out.println("Время выполнения кончилось" );
        Thread.currentThread().interrupt();
    }
}