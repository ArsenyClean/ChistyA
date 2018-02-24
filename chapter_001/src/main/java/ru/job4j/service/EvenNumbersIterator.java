package ru.job4j.service;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenNumbersIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class EvenNumbersIterator implements Iterator {

    public static void main(String[] args){

    }

    private int [] Array;
    public int indexI = 0;

    public EvenNumbersIterator(final int[] Array) {
        this.Array = Array;
    }

    @Override
    public boolean hasNext()  {
        int copyIndexI = indexI;
        while (Array.length > copyIndexI) {
            if (Array[copyIndexI] % 2 == 0)
                return true;
            else
                copyIndexI++;
        }
        return false;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (indexI >= Array.length)
            throw new NoSuchElementException();
        if (!hasNext())
            return null;
        while (indexI <= Array.length) {
             if (Array[indexI] % 2 == 0) {
                 indexI++;
                 return Array[indexI - 1];
             }
             else
                 indexI++;
        }
        return null;
    }
}
