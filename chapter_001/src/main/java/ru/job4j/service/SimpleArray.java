package ru.job4j.service;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Chisty Arseny
 * @since 19.02.2018
 * @param <T> обобщенный тип
 */

@ThreadSafe
public class SimpleArray<T> implements Iterable<T> {

    @GuardedBy("this")
    private Object[] objects;
    @GuardedBy("this")
    public int index = 0;
    @GuardedBy("this")
    private int iteratorIndex = 0;

    public SimpleArray(int size) {
        synchronized (this) {
            this.objects = new Object[size];
        }
    }

    public SimpleArray(Object[] array) {
        synchronized (this) {
            this.objects = array;
        }
    }

    public void add(T model) {
        synchronized (this) {
            while (objects.length > index) {
                if (objects[index] == null) {
                    objects[index] = model;
                    index++;
                    return;
                }
                index++;
            }
        }
    }

    public void set(int position, T model) {
        synchronized (this) {
            if (position < objects.length) {
                this.objects[position] = model;
            }
        }
    }

    public void delete(int position) throws NoSuchElementException {
        if (position >= objects.length) {
            throw new NoSuchElementException();
        }
        synchronized (this) {
            while (objects.length > position + 1) {
                objects[position] = objects[position + 1];
                position++;
            }
            objects[position] = null;
        }
    }

    public T get(int position) throws NoSuchElementException {
        synchronized (this) {
            if (position < objects.length) {
                return (T) this.objects[position];
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        synchronized (this) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    String string = "[" + i + "]=" + objects[i] + " ";
                    builder.append(string);
                }
            }
            return builder.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() throws NoSuchElementException {
                synchronized (this) {
                    if (iteratorIndex < objects.length) {
                        return objects[iteratorIndex] != null;
                    }
                    throw new NoSuchElementException();
                }
            }
            @Override
            public T next() throws NoSuchElementException {
                synchronized (this) {
                    if (hasNext()) {
                        iteratorIndex++;
                        return (T) objects[iteratorIndex - 1];
                    }
                    throw new NoSuchElementException();
                }
            }
        };
    }
}