package ru.job4j.service;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenNumbersIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class EvenNumbersIterator implements Iterator {

    private int[] array;
    private int indexI = 0;

    public EvenNumbersIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        int copyIndexI = indexI;
        while (array.length > copyIndexI) {
            if (array[copyIndexI] % 2 == 0) {
                return true;
            } else {
                copyIndexI++;
            }
        }
        return false;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (indexI >= array.length) {
            throw new NoSuchElementException();
        }
        if (!hasNext()) {
            return null;
        }
        while (indexI <= array.length) {
             if (array[indexI] % 2 == 0) {
                 indexI++;
                 return array[indexI - 1];
             } else {
                 indexI++;
             }
        }
        return null;
    }
}
