package ru.job4j.service;

import ru.job4j.lists.SimpleContainer;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

    public void ensureCapacity() {
        if (this.container.length <= index) {
            this.container = increaseContain(this.container);
        }
    }

    @Override
    public void add(Object value) {
        ensureCapacity();
        this.container[index++] = value;
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
                return container.length > index;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return container[iteratorIndex++];
                }
                throw new NoSuchElementException();
            }

            private void  modCheck() {
                if (expectedModCount    != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}