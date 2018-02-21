package ru.job4j.service;

public class SimpleStack<E>  {

    Node Stack;

    public SimpleStack(){
        this.Stack = null;
    }

    public SimpleStack(E value){
        this.Stack = new Node(value);
    }

    public E poll() {
        Node counterNode = Stack;
        while (counterNode.next != null){
            counterNode = counterNode.next;
        }
        if (counterNode.prev != null) {
            counterNode.prev.next = null;
            return (E) counterNode.value;
        }
        return null;
    }

    public void push(E value) {
        this.Stack.add(value);
    }
}