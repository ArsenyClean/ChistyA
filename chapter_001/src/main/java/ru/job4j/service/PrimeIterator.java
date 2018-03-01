package ru.job4j.service;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenNumbersIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class PrimeIterator implements Iterator {

    private int[] array;
    private int indexI = 0;

    public PrimeIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        int result = reacher();
        return result >= 0;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = reacher();
        indexI++;
        return array[result];
    }

    public int reacher() {
        while (array.length > indexI) {
            if (array[indexI] == 1) {
                indexI++;
            } else {
                if (array[indexI] == 2) {
                    return indexI;
                }
                boolean flagIsFind = true;
                for (int i = 2; i < array[indexI]; i++) {
                    if (array[indexI] % i == 0) {
                        flagIsFind = false;
                        break;
                    }
                }
                if (flagIsFind) {
                    return indexI;
                }
                indexI++;
            }
        }
        return -1;
    }
}