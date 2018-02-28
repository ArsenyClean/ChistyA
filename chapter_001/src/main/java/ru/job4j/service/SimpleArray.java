package ru.job4j.service;

import java.util.Iterator;

/**
 * @author Chisty Arseny
 * @since 19.02.2018
 * @param <T>
 */
public class SimpleArray <T extends Base> implements Iterable<T>{

    Base[] objects;
    int index = 0;
    int indexIterator = 0;

    /**
     * Конструктор, инициализирует массив и задает его длинну
     * @param size
     */
    public SimpleArray(int size){
        this.objects = new Base[size];
    }

    /**
     * add добавляет элемент в массив
     * @param model
     */
    public void add(T model){
        this.objects[index] = model;
        index++;
    }

    /**
     * set заменяет элемент массива
     * @param position
     * @param model
     */
    public void set(int position, T model){
        this.objects[position] = model;
    }

    /**
     * Метод удаляет элемент из массива
     * @param position
     */
    public void delete(int position) {
        int i = position + 1;
        while (i < objects.length) {
            objects[position] = objects[i];
            i++;
            position++;
            index--;
        }
    }

    /**
     * Метод возвращает искомый элемент массива
     * @param position
     */
    public T get(int position){
        return (T) this.objects[position];
    }

    /**
     * Метод реализует итератор
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return  (objects.length >= indexIterator);
            }
            @Override
            public T next() {
                indexIterator++;
                return (T) objects[indexIterator - 1];
            }
        };
    }

}