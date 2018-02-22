package ru.job4j.service;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable{
    int itertatoIndex = 0;
    Node Queu;

    public SimpleSet(E value){
        this.Queu = new Node(value);
    }

    public void add(E value) {
        Node copyNode = this.Queu;
        while (copyNode.next != null){
            if (copyNode.value == value)
                return;
            copyNode = copyNode.next;
        }
        this.Queu.add(value);
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                int i = 0;
                Node copyNode = Queu;
                while (i < itertatoIndex && copyNode.next != null) {
                    copyNode = copyNode.next;
                    i++;
                }
                return copyNode.next != null;
            }

            @Override
            public Object next() {
                int i = 0;
                Node copyNode = Queu;
                while (i < itertatoIndex && copyNode.next != null) {
                    copyNode = copyNode.next;
                    i++;
                }
                itertatoIndex++;
                if (itertatoIndex > Queu.index)
                    return null;
                return copyNode.next.value;
            }
        };
    }
}