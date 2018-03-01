package ru.job4j.service;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Class LinkedList
 * @author Chisty Arseny
 * @since 16.02.2018
 */
public class LinkedList<E> implements SimpleContainer, Iterable {

    public Node first = new Node();
    int index = 0;
    private int iterIndex = 0;

    private int expectedModCount = 0;
    private int modCount = 0;

    public static class Node<E> {

        Node next;
        Node prev;
        E value;

        public Node() {
            this.next = null;
            this.value = null;
            this.prev = null;
        }

        public Node(E value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    boolean hasCycle() {
        Node rabbit = this.first;
        Node turrtle = this.first;
        while (rabbit.next != null) {
            rabbit = rabbit.next.next;
            turrtle = turrtle.next;
            if (rabbit == turrtle) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        Node copyNode = first;
        while (copyNode.next != null) {
            String string = "[" + i + "]=" + copyNode.next.value.toString() + " ";
            builder.append(string);
            i++;
            copyNode = copyNode.next;
        }
        return builder.toString();
    }

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
        Node counterNode = this.first;
        while (counterNode.next != null) {
            counterNode = counterNode.next;
        }
        counterNode.next = newNode;
        newNode.prev = counterNode;
        modCount++;
        index++;
    }

    @Override
    public Object get(int index) {
        Node counterNode = this.first;
        int counter = 0;
        while (counter < index && counterNode != null) {
            counterNode = counterNode.next;
            counter++;
        }
        if (counterNode == null) {
            return null;
        }
        return counterNode.value;
    }

    @Override
    public Iterator iterator() {
        expectedModCount = modCount;
        Node currentNode = this.first;
        return new Iterator() {
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount == modCount) {
                    Node counterNode = currentNode;
                    counterNode = reacher(counterNode);
                    return counterNode.next != null;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public Object next() {
                if (expectedModCount == modCount) {
                    Node counterNode = currentNode;
                    counterNode = reacher(counterNode);
                    iterIndex++;
                    if (counterNode == null) {
                        return null;
                    }
                    return counterNode.value;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            Node reacher(Node node) {
                int counter = 0;
                while (iterIndex > counter && node != null) {
                    node = node.next;
                    counter++;
                }
                return node;
            }
        };
    }
}
