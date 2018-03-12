package ru.job4j.currency.thread.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {

    private int value;

    public synchronized void increment() {
        this.value++;
    }

    @GuardedBy("this")
    public synchronized int get() {
        return this.value;
    }
}