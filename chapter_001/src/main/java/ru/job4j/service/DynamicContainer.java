package ru.job4j.service;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicContainer <E> implements SimpleContainer, Iterable {

    Object[] container;
    int index = 0;
    int iteratorIndex = 0;
    int modCount = 0;

    public DynamicContainer(Object[] container) {
        this.container = container;
        modCount++;
    }

    public Object[] IncreaseContainer(Object[] container) {
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
            this.container = IncreaseContainer(this.container);
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
                    if (expectedModCount == modCount)
                        return container.length > iteratorIndex;
                    else
                        throw new ConcurrentModificationException();
                } catch (ConcurrentModificationException e) {
                    return false;
                }
            }

            @Override
            public Object next() {
                try {
                    if (expectedModCount == modCount)
                        return container[iteratorIndex];
                    else
                        throw new ConcurrentModificationException();
                } catch (ConcurrentModificationException e) {
                    return null;
                }
            }
        };
    }
}

