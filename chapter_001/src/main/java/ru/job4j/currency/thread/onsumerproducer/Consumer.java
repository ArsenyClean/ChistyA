package ru.job4j.currency.thread.onsumerproducer;

public class Consumer implements Runnable {

    private SimpleBlockingQueue<Integer> simpleQ;
    private Integer count = 0;

    public Consumer(SimpleBlockingQueue<Integer> simpleQ) {
        this.simpleQ = simpleQ;
    }

    public void setCountOfPeks(Integer count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
            while (count > 0) {
                System.out.println(simpleQ.peek());
                count--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}