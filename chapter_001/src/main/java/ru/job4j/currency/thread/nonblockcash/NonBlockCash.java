package ru.job4j.currency.thread.nonblockcash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class NonBlockCash<K, V extends Model> {

    ConcurrentHashMap<K, V> hashMap;

    public NonBlockCash() {
        hashMap = new ConcurrentHashMap<>();
    }

    public void add(K key, V model) {
        hashMap.putIfAbsent(key, model);
    }

    public void update(K key, Model model) {
        hashMap.computeIfPresent(key, new BiFunction<K, V, V>() {
            @Override
            public V apply(K k, V v) {
                if (model.getVersion() == v.getVersion()) {
                    v.setName(model.getName());
                } else {
                    try {
                        throw new OplimisticException();
                    } catch (OplimisticException e) {
                        e.printStackTrace();
                    }
                }
                return v;
            }
        });
    }

    public void delete(K key) {
        hashMap.remove(key);
    }

    public static class OplimisticException extends Exception {

        public OplimisticException() {
            super("Versions are not the same");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<K, V> model : hashMap.entrySet()) {
            stringBuilder.append(model.getKey()).append(" ").append(model.getValue().getName()).append("\n");
        }
        return stringBuilder.toString();
    }
}