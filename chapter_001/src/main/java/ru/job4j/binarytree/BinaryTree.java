package ru.job4j.binarytree;

import java.util.*;

public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {

    private Node<E> root;
    private int modCount = 0;

    public BinaryTree(E first) {
        root = new Node<E>(first);
    }

    public class Node<E extends Comparable<E>> {
        Node<E> left;
        Node<E> right;
        E value;

        public Node(E e) {
            this.value = e;
        }

        public int compareTo(E o) {
            return this.value.compareTo(o);
        }
    }

    public void add(E e) {
        reacher(this.root, this.root, e, 0);
        modCount++;
    }

    private void reacher(Node<E> prev, Node<E> node, E e, int flagLefrOrRight) {
        if (node == null) {
            if (flagLefrOrRight < 0) {
                prev.left = new Node<E>(e);
                return;
            }
            if (flagLefrOrRight > 0) {
                prev.right = new Node<E>(e);
                return;
            } else {
                prev = new Node<E>(e);
            }
        }
        prev = node;
        int comp = node.compareTo(e);
        if (comp > 0) {
            reacher(prev, node.left, e, -1);
        }
        if (comp < 0) {
            reacher(prev, node.right, e, 1);
        }
    }

    @Override
    public String toString() {
        Queue<Node<E>> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        queue.offer(this.root);
        builder.append(this.root.value + ", ");
        while (!queue.isEmpty()) {
            Node<E> data = queue.poll();
            if (data.left != null) {
                queue.offer(data.left);
                builder.append(data.left.value + ", ");
            }
            if (data.right != null) {
                queue.offer(data.right);
                builder.append(data.right.value + ", ");
            }
        }
        return builder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Queue<Node<E>> queue = new LinkedList<>();
        List<Node<E>> list = new ArrayList<>();
        queue.offer(this.root);
        list.add(this.root);
        while (!queue.isEmpty()) {
            Node<E> data = queue.poll();
            if (data.left != null) {
                queue.offer(data.left);
                list.add(data.left);
            }
            if (data.right != null) {
                queue.offer(data.right);
                list.add(data.right);
            }
        }
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                checkModCount();
                return index < list.size();
            }

            @Override
            public E next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return list.get(index++).value;
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}