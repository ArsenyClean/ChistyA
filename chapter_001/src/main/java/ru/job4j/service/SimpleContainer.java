package ru.job4j.service;

public interface SimpleContainer<E> {

    void add(E value);
    E get(int index);
}
