package ru.job4j.service;

import ru.job4j.lists.SimpleContainer;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicContainer<E> implements SimpleContainer, Iterable {

    private Object[] container;
    private int index = 0;
    private int modCount = 0;

    public DynamicContainer(Object[] container) {
        this.container = container;
    }

    private Object[] increaseContain(Object[] container) {
        Object[] copyContainer = Arrays.copyOf(container, container.length * 2);
        modCount++;
        return copyContainer;
    }

    @Override
    public void add(Object value) {
        if (this.container.length > index) {
            this.container[index] = value;
            index++;
        } else {
            this.container = increaseContain(this.container);
            this.container[index] = value;
            index++;
        }
        modCount++;
    }

    @Override
    public Object get(int index) {
        return this.container[index];
    }

    @Override
    public Iterator iterator() {
        int expectedModCount = modCount;
        return new Iterator() {
            private int iteratorIndex = 0;
            @Override
            public boolean hasNext() {
                modCheck();
                return container.length > iteratorIndex;
            }

            @Override
            public Object next() {
                hasNext();
                iteratorIndex++;
                return container[iteratorIndex - 1];
            }

            private void  modCheck() {
                if (expectedModCount == modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
