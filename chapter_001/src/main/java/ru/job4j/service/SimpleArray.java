package ru.job4j.service;

import java.util.Iterator;

/**
 * @author Chisty Arseny
 * @since 19.02.2018
 * @param <T>
 */
public class SimpleArray <T> implements Iterable<T>{

    Object[] objects;
    int index = 0;
    int indexIterator = 0;

    public SimpleArray(int size){
        this.objects = new Object[size];
    }

    public void add(T model){

        this.objects[index] = model;
        index++;
    }

    public void set(int position, T model){

        this.objects[position] = model;
    }

    public void delete(int position) {
        int i = position + 1;
        while (i < objects.length) {
            objects[position] = objects[i];
            i++;
            position++;
            index--;
        }
    }

    public T get(int position){

        return (T) this.objects[position];
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (objects.length >= indexIterator )
                    return true;
                return false;
            }

            @Override
            public T next() {
                indexIterator++;
                return (T) objects[indexIterator - 1];
            }
        };
    }

}