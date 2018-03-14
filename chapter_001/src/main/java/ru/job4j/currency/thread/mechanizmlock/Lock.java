package ru.job4j.currency.thread.mechanizmlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class Lock {

    AtomicBoolean isLocked = new AtomicBoolean(true);

    public void lock() {
            if (!isLocked.get()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLocked.set(true);
        }

    public void unlock(Object o) {
        isLocked.set(false);
        notify();
    }
}