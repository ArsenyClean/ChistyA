package ru.job4j.currency.thread.onsumerproducer;

public class Producer implements Runnable {

    private SimpleBlockingQueue<Integer> simpleQ;
    private Integer[] array = new Integer[0];

    public Producer(SimpleBlockingQueue<Integer> simpleQ) {
        this.simpleQ = simpleQ;
    }

    public void addArrayOfNumbersToAdd(Integer[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        try {
            for (Integer integer : array) {
                simpleQ.offer(integer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}