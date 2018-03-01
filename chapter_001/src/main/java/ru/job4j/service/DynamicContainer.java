package ru.job4j.service;

import ru.job4j.lists.SimpleContainer;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicContainer<E> implements SimpleContainer, Iterable {

    private Object[] container;
    private int index = 0;
    private int iteratorIndex = 0;
    private int modCount = 0;

    public DynamicContainer(Object[] container) {
        this.container = container;
        modCount++;
    }

    private Object[] increaseContain(Object[] container) {
        int index = 0;
        Object[] copyContainer = new Object[container.length * 2];
        while (container.length > index) {
            copyContainer[index] = container[index];
            index++;
        }
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
        modCount++;
        return this.container[index];
    }

    @Override
    public Iterator iterator() {
        int expectedModCount = modCount;
        return new Iterator() {
            @Override
            public boolean hasNext() {
                try {
                    if (expectedModCount == modCount) {
                        return container.length > iteratorIndex;
                    } else {
                        throw new ConcurrentModificationException();
                    }
                } catch (ConcurrentModificationException e) {
                    return false;
                }
            }

            @Override
            public Object next() {
                try {
                    if (expectedModCount == modCount) {
                        return container[iteratorIndex];
                    } else {
                        throw new ConcurrentModificationException();
                    }
                } catch (ConcurrentModificationException e) {
                    return null;
                }
            }
        };
    }
}
