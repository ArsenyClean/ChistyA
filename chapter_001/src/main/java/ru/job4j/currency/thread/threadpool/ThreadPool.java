package ru.job4j.currency.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    List<ThreadPoolThread> threads;
    Queue<Work> queue = new ConcurrentLinkedQueue<>();
    AtomicInteger size;

    public ThreadPool(int size) {
        this.size = new AtomicInteger(size);
        if (size > 1) {
            threads = new ArrayList<>(size);
        } else {
            threads = new ArrayList<>(1);
        }
    }

    public class ThreadPoolThread extends Thread {

        Queue<Work> queue;
        Thread thread;

        public ThreadPoolThread(Queue<Work> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                thread = new Thread(queue.poll());
                thread.run();
            }
        }
    }

    public void add(Work work) {
            queue.add(work);
    }

    public void starter() throws InterruptedException {
        for (int i = 0; i < this.size.get(); i++) {
            ThreadPoolThread thread = new ThreadPoolThread(queue);
            thread.start();
            threads.add(thread);
        }
        while (true) {
            boolean flag = true;
            for (Thread thread : threads) {
                while (thread.isAlive()) {
                    flag = false;
                }
            }
            if (flag) {
                return;
            }
        }
    }
}