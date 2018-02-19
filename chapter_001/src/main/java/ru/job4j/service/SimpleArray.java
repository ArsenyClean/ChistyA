package ru.job4j.service;

import java.util.Iterator;

/**
 * TODO: comment
 * @author Chisty Arseny
 * @since 19.02.2018
 * @param <T>
 */
public class SimpleArray <T> implements Iterable<T>{

    Object[] objects;
    int index = 0;

    public SimpleArray(int size){
        this.objects = new Object[size];
    }

    public void add(T model){
        this.objects[index] = model;
    }

    public void set(int position, T model){
        this.objects[position] = model;
    }

    public void delete(int position){
        int i = position + 1;
        while (objects[i] != null){
            objects[i] = objects[position];
            i++;
            position++;
        }
    }

    public T get(int position){
        return (T) this.objects[position];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
