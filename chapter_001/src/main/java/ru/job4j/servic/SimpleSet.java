package ru.job4j.servic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet implements Iterator {
    Object[] container;
    int index = 0;
    int iteratorIndex = 0;

    public SimpleSet() { }

    public SimpleSet(int size) {
        this.container = new Object[size];
    }

    @Override
    public String toString() {
        StringBuilder  builder = new StringBuilder();
        int i = 0;
        while (container.length > i) {
            String string = "[" + i + "]=" + (String) container[i] + " ";
            builder.append(string);
            i++;
        }
        return builder.toString();
    }

    public Object[] increaseContainer(Object[] container) {
        Object[] copy = Arrays.copyOf(container, container.length * 2);
        return copy;
    }

    public void add(Object value) {
        int result = reacher(container, value);
        if (result >= 0) {
            return;
        } else {
            if (container.length > index) {
                this.container[index] = value;
                index++;
            } else {
                this.container = increaseContainer(this.container);
                this.container[index] = value;
                index++;
            }
        }
    }

    public int reacher(Object[] container, Object value) {
        int i = 0;
        while (index > i) {
            if (container[i].equals(value)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        if (index > iteratorIndex) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() throws NoSuchElementException{
        if (hasNext()) {
            iteratorIndex++;
            return container[iteratorIndex - 1];
        }
        throw new NoSuchElementException();
    }
}