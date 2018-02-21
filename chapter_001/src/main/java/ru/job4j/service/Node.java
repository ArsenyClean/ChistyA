package ru.job4j.service;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Node <E> implements SimpleContainer, Iterator {

    Node next;
    Node prev;
    E value;
    int index = 0;
    int iterIndex = 0;

    int expectedModCount = 0;
    int modCount = 0;

    public Node(Object value) {
        this.value = (E) value;
        this.prev = null;
        this.next = null;
    }

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
        Node counterNode = this;
        while (counterNode.next != null)
            counterNode = counterNode.next;
        counterNode.next = newNode;
        newNode.prev = counterNode;
        modCount++;
        index++;
    }

    @Override
    public Object get(int index) {
        Node counterNode = this;
        int counter = 0;
        while (counter < index && counterNode != null) {
            counterNode = counterNode.next;
            counter++;
        }
        if (counterNode == null)
                return null;
        return counterNode.value;
    }

    @Override
    public boolean hasNext() {
        expectedModCount = modCount;
        try {
            if (expectedModCount == modCount) {
                Node counterNode = this;
                int counter = 0;
                while (iterIndex > counter && counterNode.next != null) {
                    counterNode = counterNode.next;
                    counter++;
                }
                return counterNode.next != null;
            }
            else
                throw new ConcurrentModificationException();
        } catch (ConcurrentModificationException e) {
            return false;
        }
    }

    @Override
    public Object next() {
        expectedModCount = modCount;
        try {
            if (expectedModCount == modCount) {
                Node counterNode = this;
                int counter = 0;
                while (iterIndex > counter && counterNode != null){
                    counterNode = counterNode.next   ;
                    counter++;
                }
                iterIndex++;
                if (counterNode == null)
                    return null;
                return counterNode.value;
            }
            else
                throw new ConcurrentModificationException();
        } catch (ConcurrentModificationException e) {
            return e;
        }
    }
}

