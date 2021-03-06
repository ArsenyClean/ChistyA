package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree, является реализацией дерева
 * @author Chisty Arseny
 * @since 5.3.2018
 * @param <E> <E>
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount = 0;

    public Tree(E first) {
        this.root = new Node(first);
    }

    private int compare(Node<E> node, E parent) {
        return node.eqValue(parent) ? 0 : -1;
    }

    private boolean findChild(Node<E> node, E child) {
        boolean result = false;
        for (Node<E> leave : node.leaves()) {
            if (!result) {
                if (leave.eqValue(child)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isBynry() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        boolean result = true;
        while (!queue.isEmpty()) {
            if (result) {
                Node<E> node = queue.poll();
                int counter = 0;
                for (Node<E> leave : node.leaves()) {
                    queue.offer(leave);
                    counter++;
                }
                if (counter > 2) {
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean flag = true;
        Optional<Node<E>> result = findBy(parent);
        if (!result.isPresent()) {
            flag = false;
        }
        if (flag) {
            Node<E> node = result.get();
            if (findChild(node, child)) {
                flag = false;
            }
            if (flag) {
                node.add(new Node(child));
                modCount++;
            }
        }
        return flag;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> variable = data.poll();
            if (variable.eqValue(value)) {
                result = Optional.of(variable);
                break;
            }
            for (Node<E> child: variable.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        Queue<Node<E>> data = new LinkedList<>();
        List<Node<E>> counter = new LinkedList<>();
        data.offer(this.root);
        counter.add(this.root);
        while (!data.isEmpty()) {
            Node<E> leaves = data.poll();
            for (Node<E> leave : leaves.leaves()) {
                data.offer(leave);
                counter.add(leave);
            }
        }
        return new Iterator() {
            int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                checkModCount();
                return index < counter.size();
            }

            @Override
            public Object next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return counter.get(index++);
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}