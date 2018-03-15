package ru.job4j.currency.thread.nonblockcash;

import java.util.concurrent.atomic.AtomicInteger;

public class Model<K, V> {

    V name;
    K key;
    private volatile int version = 0;

    public Model(K key, V name) {
        this.name = name;
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public V getName() {
        return name;
    }

    public void setName(V name) {
        this.name = name;
        version++;
    }
}
