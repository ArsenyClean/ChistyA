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
        int result = reacher();
        return result >= 0;
    }

    @Override
    public Object next() throws NoSuchElementException {
        int result = reacher();
        if (result >= 0) {
            indexI = result + 1;
            return array[result];
        }
        throw new NoSuchElementException();
    }

    public int reacher() {
        int copyIndex = indexI;
        while (copyIndex < array.length) {
            if (array[copyIndex] % 2 == 0) {
                return copyIndex;
            } else {
                copyIndex++;
            }
        }
        return -1;
    }
}