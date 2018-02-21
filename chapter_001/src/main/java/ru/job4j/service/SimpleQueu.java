package ru.job4j.service;

public class SimpleQueu <E> {

    Node Queu;
    boolean end = false;

    public SimpleQueu(){
        this.Queu = null;
    }

    public SimpleQueu(E value){
        this.Queu = new Node(value);
    }

    public E poll() {
        if (end)
            return null;
        if (Queu.next == null) {
            end = true;
            return (E) Queu.value;
        }
        E valuee = (E) Queu.value;
        Queu = Queu.next;
        Queu.prev.next = null;
        Queu.prev = null;
        return valuee;
    }

    public void push(E value) {
        this.Queu.add(value);
        end = false;
    }
}
