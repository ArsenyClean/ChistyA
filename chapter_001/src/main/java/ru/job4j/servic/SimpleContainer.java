package ru.job4j.servic;

public interface SimpleContainer<E> {

    void add(E value);
    E get(int index);
}
