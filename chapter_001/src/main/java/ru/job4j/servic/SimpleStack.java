package ru.job4j.servic;

public class SimpleStack<E> {

    LinkedList stack = new LinkedList();
    LinkedList.Node node = stack.first;

    public SimpleStack() { }

    public SimpleStack(E value) {
        this.node = new LinkedList.Node(value);
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

    public E poll() {
        LinkedList.Node counterNode = node;
        while (counterNode.next != null) {
            counterNode = counterNode.next;
        }
        if (counterNode.prev != null) {
            counterNode.prev.next = null;
            return (E) counterNode.value;
        }
        return null;
    }

    public void push(E value) {


        this.stack.add(value);
    }
}