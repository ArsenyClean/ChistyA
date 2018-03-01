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
        int copyIndexI = indexI;
        while (array.length > copyIndexI) {
            boolean flagIsFind = true;
            for (int i = array[indexI] - 1; i > 1; i--) {
                if (array[indexI] % i == 0) {
                    flagIsFind = false;
                    break;
                }
            }
            if (flagIsFind) {
                return true;
            }
            copyIndexI++;
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
        while (array.length > indexI) {
            if (array[indexI] == 1) {
                indexI++;
            }
            if (array[indexI] == 2) {
                indexI++;
                return array[indexI - 1];
            }
            boolean flagIsFind = true;
            for (int i = 2; i < array[indexI]; i++) {
                if (array[indexI] % i == 0) {
                    flagIsFind = false;
                    break;
                }
            }
            if (flagIsFind) {
                indexI++;
                return array[indexI - 1];
            }
            indexI++;
        }
        return null;
    }
}