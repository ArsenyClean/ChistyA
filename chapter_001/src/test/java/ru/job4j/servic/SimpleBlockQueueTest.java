package ru.job4j.servic;

import org.junit.Test;
import ru.job4j.currency.thread.bomber.BomberMan;
import ru.job4j.currency.thread.nonblockcash.Model;
import ru.job4j.currency.thread.nonblockcash.NonBlockCash;
import ru.job4j.currency.thread.onsumerproducer.Consumer;
import ru.job4j.currency.thread.onsumerproducer.Producer;
import ru.job4j.currency.thread.onsumerproducer.SimpleBlockingQueue;
import ru.job4j.currency.thread.parallelsearch.ParallelSearch;
import ru.job4j.currency.thread.threadpool.ThreadPool;
import ru.job4j.currency.thread.threadpool.Work;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

public class SimpleBlockQueueTest {

//    @Test
//    public void Test() throws InterruptedException {
//
//        SimpleBlockingQueue<Integer> simpleQ = new SimpleBlockingQueue<>(5);
//
//        Producer p = new Producer(simpleQ);
//        Consumer c = new Consumer(simpleQ);
//
//        p.addArrayOfNumbersToAdd(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14});
//        c.setCountOfPeks(12);
//
//        Thread producer = new Thread(p);
//        Thread consumer = new Thread(c);
//
//        producer.start();
//        consumer.start();
//
//        producer.join();
//        consumer.join();
//    }

    @Test
    public void TestOfThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(2);
        threadPool.add(new Work());
        threadPool.add(new Work());
        threadPool.add(new Work());
        threadPool.starter();
    }

    @Test
    public void testOfNonBlockCash() {
        NonBlockCash nonBlockCash = new NonBlockCash();
        nonBlockCash.add(1, new Model(1, " model 1 "));
        nonBlockCash.add(2, new Model(2, " model 2 "));
        nonBlockCash.add(3, new Model(3, " model 3 "));
        System.out.println(nonBlockCash.toString());
        nonBlockCash.update(1, new Model(1, " model 2 "));
        System.out.println(nonBlockCash.toString());
    }

    @Test
    public void testOf() {
//        Random random= new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(1 + random.nextInt(4));
//        }
        BomberMan bomberMan = new BomberMan(7, 4);
        bomberMan.starter();
    }
//
//    @Test
//    public void Test3() throws InterruptedException {
////        Integer integer = 0;
////        TestingLock r = new TestingLock(integer);
////        Thread thread = new Thread(r);
////        Thread thread1 = new Thread(r);
////        thread.start();
////        thread1.start();
////        thread1.join();
////        thread.join();
//    }
//
//    @Test
//    public void testin() throws InterruptedException {
//        List<String> s = new ArrayList<>();
//        s.add("txt"); s.add("java");
//        ParallelSearch p = new ParallelSearch("C:\\projects\\ChistyA\\filesArchive"," ",s);
//        p.init();
//    }
}