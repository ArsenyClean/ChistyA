package ru.job4j.servic;

import org.junit.Test;
import ru.job4j.currency.thread.onsumerproducer.Consumer;
import ru.job4j.currency.thread.onsumerproducer.Producer;
import ru.job4j.currency.thread.onsumerproducer.SimpleBlockingQueue;
import ru.job4j.currency.thread.userstorage.User;
import ru.job4j.currency.thread.userstorage.UserStorage;
import ru.job4j.currency.thread.count.Count;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockQueueTest {

    @Test
    public void Test() throws InterruptedException {

        SimpleBlockingQueue<Integer> simpleQ = new SimpleBlockingQueue<>(5);

        Producer p = new Producer(simpleQ);
        Consumer c = new Consumer(simpleQ);

        p.addArrayOfNumbersToAdd(new Integer[]{1,2,3,4,5,6,7,8});
        c.setCountOfPeks(9);

        Thread producer = new Thread(p);
        Thread consumer = new Thread(c);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

}