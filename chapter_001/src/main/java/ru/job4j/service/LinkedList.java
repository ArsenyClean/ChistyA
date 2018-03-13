package ru.job4j.service;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * Class LinkedList
 * @author Chisty Arseny
 * @since 16.02.2018
 */
@ThreadSafe
public class LinkedList<E> implements SimpleContainer<E>, Iterable<E> {

    @GuardedBy("this")
    Node<E> first;

    public LinkedList() {
        synchronized (this) {
            first = new Node<E>();
        }
    }

    private int modCount = 0;

    public static class Node<E> {

        Node<E> next;
        Node<E> prev;
        E value;

        public Node() {
            synchronized (this) {
                this.next = null;
                this.value = null;
                this.prev = null;
            }
        }

        public Node(E value) {
            synchronized (this) {
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }
    }

    boolean hasCycle() {
        synchronized (this) {
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
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        synchronized (this) {
            Node copyNode = first;
            while (copyNode.next != null) {
                String string = "[" + i + "]=" + copyNode.next.value.toString() + " ";
                builder.append(string);
                i++;
                copyNode = copyNode.next;
            }
            return builder.toString();
        }
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<E>(value);
        synchronized (this) {
            Node<E> counterNode = this.first;
            while (counterNode.next != null) {
                counterNode = counterNode.next;
            }
            counterNode.next = newNode;
            newNode.prev = counterNode;
            modCount++;
        }
    }

    @Override
    public E get(int index) {
        synchronized (this) {
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
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> currentNode = first.next;

            @Override
            public boolean hasNext() {
                synchronized (this) {
                    return currentNode != null;
                }
            }

            @Override
            public E next() {
                checkForCoModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                synchronized (this) {
                    E result = currentNode.value;
                    currentNode = currentNode.next;
                    return result;
                }
            }

            private void checkForCoModification() {
                synchronized (this) {
                    if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException();
                    }
                }
            }
        };
    }
}