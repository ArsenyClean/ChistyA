package ru.job4j.currency.thread.onsumerproducer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int index = 0, indexLimit = 10;

    public SimpleBlockingQueue(int indexLimit) {
        synchronized (this) {
            if (indexLimit >= 1) {
                this.indexLimit = indexLimit;
            } else {
                this.indexLimit = 1;
            }
        }
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (index > indexLimit) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            queue.add(value);
            index++;
            notify();
        }
    }

    public T peek() throws InterruptedException {
        synchronized (this) {
            while (index < 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            T result = queue.poll();
            index--;
            notify();
            return result;
        }
    }
}