package ru.job4j.mapservice;

import java.util.*;

/**
 * HashMap класс, реализующий копию HashMap стандартной библиотеки
 * @param <K> тип для ключа
 * @param <V> тип для значения
 * @author Chisty Arseny
 * @since 4.3.2018
 */
public class HashMap<K, V> implements Iterable<V> {

    private KeyValue[] array;
    private int full = 0;
    private int modCount = 0;

    public HashMap(int size) {
        array = new KeyValue[size];
    }

    public class KeyValue<K, V> {
        K key;
        V value;

        KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean insert(K key, V value) {
        int hash = hashMake(key);
        int position = indexMake(hash, array.length);
        if (!checkForExist(position)) {
            return false;
        }
        modCount++;
        full++;
        KeyValue<K, V> newKeyValue = new KeyValue<K, V>(key, value);
        array[position] = newKeyValue;
        checkFull();
        return true;
    }

    private void checkFull() {
        float k = (float) full / (float) array.length;
        if (k > 0.5) {
            array = growTable();
        }
    }

    public V get(K key) {
        int hash = hashMake(key);
        int position = indexMake(hash, array.length);
        if (!checkForExist(position)) {
            return null;
        }
        return (V) array[position].value;
    }

    public boolean delete(K key) {
        int hash = hashMake(key);
        int position = indexMake(hash, array.length);
        if (checkForExist(position)) {
            return false;
        }
        modCount++;
        full--;
        array[position] = null;
        return true;
    }

    private KeyValue<K, V>[] growTable() {
        HashMap<K, V> newHashMap = new HashMap<K, V>(array.length * 2);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                newHashMap.insert((K) array[i].key, (V) array[i].value);
            }
        }
        modCount++;
        return newHashMap.array;
    }

    private boolean checkForExist(int position) {
        if (position >= array.length) {
            throw new NoSuchElementException();
        }
        return array[position] == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                String string = "key=" + array[i].key + " value=" + array[i].value + "; ";
                builder.append(string);
            }
        }
        builder.append("}");
        return builder.toString();
    }

    private int hashMake(K key) {
        return (key != null) ? key.hashCode() : 0;
    }

    private int indexMake(int hash, int length) {
        return ((hash * 31) & 0x7fffffff) % length;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                checkModCount();
                return (searchForNext() > 0);
            }

            @Override
            public V next() {
                checkModCount();
                int result = searchForNext();
                if (result >= 0) {
                    index = result + 1;
                    return (V) array[result].value;
                }
                throw new NoSuchElementException();
            }

            private boolean checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }

            private int searchForNext() {
                for (int i = index; i < array.length; i++) {
                    if (array[i] != null) {
                        return i;
                    }
                }
                return -1;
            }
        };
    }
}