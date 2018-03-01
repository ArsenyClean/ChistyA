package ru.job4j.service;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class LinkedList
 * @author Chisty Arseny
 * @since 16.02.2018
 */
public class LinkedList<E> implements SimpleContainer<E>, Iterable<E> {

    public Node<E> first = new Node<E>();

    private int modCount = 0;

    public static class Node<E> {

        Node<E> next;
        Node<E> prev;
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
    public void add(E value) {
        Node<E> newNode = new Node<E>(value);
        Node<E> counterNode = this.first;
        while (counterNode.next != null) {
            counterNode = counterNode.next;
        }
        counterNode.next = newNode;
        newNode.prev = counterNode;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> counterNode = this.first;
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> currentNode = first.next;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                checkForCoModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = currentNode.value;
                currentNode = currentNode.next;
                return result;
            }

            private void checkForCoModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}