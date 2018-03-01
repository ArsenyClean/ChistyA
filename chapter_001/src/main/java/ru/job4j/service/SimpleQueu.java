package ru.job4j.service;

import java.util.NoSuchElementException;

public class SimpleQueu<E> {
    private LinkedList<E> queu = new LinkedList<E>();
    private LinkedList.Node<E> node = queu.first;
    private boolean end = false;

    public SimpleQueu() { }

    public SimpleQueu(E value) {
        this.node = new LinkedList.Node<E>(value);
    }

    public E poll() {
        if (node.next == null) {
            throw new NoSuchElementException();
        }
        E valuee = node.next.value;
        node = node.next;
        node.prev.next = null;
        node.prev = null;
        return valuee;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        LinkedList.Node copyNode = node;
        while (copyNode.next != null) {
            String string = "[" + i + "]=" + copyNode.next.value.toString() + " ";
            builder.append(string);
            i++;
            copyNode = copyNode.next;
        }
        return builder.toString();
    }

    public void push(E value) {
        this.queu.add(value);
        end = false;
    }
}