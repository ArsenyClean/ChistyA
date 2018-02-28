package ru.job4j.servic;

public class SimpleQueu<E> {

    private LinkedList queu = new LinkedList();
    private LinkedList.Node node = queu.first;
    private boolean end = false;

    public SimpleQueu() { }

    public SimpleQueu(E value) {
        this.node = new LinkedList.Node(value);
    }

    public E poll() {
        if (end) {
            return null;
        }
        if (node.next == null) {
            end = true;
            return (E) node.value;
        }
        E valuee = (E) node.value;
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