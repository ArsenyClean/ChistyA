package ru.job4j.service;

import javafx.scene.effect.SepiaTone;

import java.util.*;
import java.util.HashSet;

public class SimpleLinkedSet<E> implements Iterable<E> {

    public int index = 0;
    int modCount = 0;
    public LinkedList<E> list = new LinkedList<E>();

    public SimpleLinkedSet(E value) {
        list.add(value);
    }
    public SimpleLinkedSet() { }


    public void add(E value) {
        LinkedList.Node copyNode = list.first;
        copyNode = reacher(copyNode, value);
        if (copyNode == null) {
            return;
        } else {
            LinkedList.Node<E> newNode = new LinkedList.Node(value);
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
        LinkedList.Node copyNode = list.first;
        while (copyNode.next != null) {
            String string = "[" + i + "]=" + copyNode.next.value.toString() + " ";
            builder.append(string);
            i++;
            copyNode = copyNode.next;
        }
        return builder.toString();
    }

    public LinkedList.Node reacher(LinkedList.Node container, E value) {
        while (container.next != null) {
            if (container.next.value.equals(value)) {
                return null;
            }
            container = container.next;
        }
        return container;
    }

    @Override
    public Iterator iterator() {
        int expectedModCount = modCount;
        LinkedList.Node currentNode = this.list.first;
        Iterator<E> it = this.list.iterator();
        return new Iterator() {
            @Override
            public boolean hasNext() {
                catcher();
                return it.hasNext();
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return it.next();
                }
                throw new NoSuchElementException();
            }

            private void catcher() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}