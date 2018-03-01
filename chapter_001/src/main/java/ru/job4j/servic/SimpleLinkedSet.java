package ru.job4j.servic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {

    private int iterIndex = 0;
    private int index = 0;
    private int expectedModCount = 0;
    private int modCount = 0;
    private LinkedList list = new LinkedList();
    private LinkedList.Node set = list.first;

    SimpleLinkedSet() { }

    public SimpleLinkedSet(E value) {
        this.set = new LinkedList.Node(value);
    }

    public void add(E value) {
        LinkedList.Node copyNode = this.set;
        copyNode = reacher(copyNode, value);
        if (copyNode == null) {
            return;
        } else {
            LinkedList.Node newNode = new LinkedList.Node(value);
            copyNode.next = newNode;
            newNode.prev = copyNode;
        }
        index++;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        LinkedList.Node copyNode = set;
        while (copyNode.next != null) {
            String string = "[" + i + "]=" + copyNode.next.value.toString() + " ";
            builder.append(string);
            i++;
            copyNode = copyNode.next;
        }
        return builder.toString();
    }

    private LinkedList.Node reacher(LinkedList.Node container, E value) {
        while (container.next != null) {
            if (container.next.value.equals(value)) {
                return null;
            }
            container = container.next;
        }
        return container;
    }

    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;
        LinkedList.Node currentNode = set;
        return new Iterator<E>() {
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount == modCount) {
                    LinkedList.Node counterNode = currentNode;
                    counterNode = reacher(counterNode);
                    return counterNode.next != null;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public E next() {
                if (expectedModCount == modCount) {
                    LinkedList.Node counterNode = currentNode;
                    counterNode = reacher(counterNode);
                    iterIndex++;
                    if (counterNode.next == null) {
                        throw new ConcurrentModificationException();
                    } else {
                        return (E) counterNode.next.value;
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            LinkedList.Node reacher(LinkedList.Node node) {
                int counter = 0;
                while (iterIndex > counter && node.next != null) {
                    node = node.next;
                    counter++;
                }
                return node;
            }
        };
    }
}