package ru.job4j.service;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenNumbersIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class PrimeIterator implements Iterator {

    public static void main(String[] args){

    }

    private int [] Array;
    public int indexI = 0;

    public PrimeIterator(final int[] Array) {
        this.Array = Array;
    }

    @Override
    public boolean hasNext()  {
        int copyIndexI = indexI;
        while (Array.length > copyIndexI) {
            boolean flagIsFind = true;
            for ( int i = Array[indexI] - 1; i > 1; i-- ){
                if ( Array[indexI] % i == 0 ){
                    flagIsFind = false;
                    break;
                }
            }
            if (flagIsFind)
                return true;
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
        while (Array.length > indexI) {
            if ( Array[indexI] == 1)
                indexI++;
            if (Array[indexI] == 2) {
                indexI++;
                return Array[indexI - 1];
            }
            boolean flagIsFind = true;
            for ( int i = 2; i < Array[indexI]; i++ ){
                if ( Array[indexI] % i == 0 ){
                    flagIsFind = false;
                    break;
                }
            }
            if (flagIsFind) {
                indexI++;
                return Array[indexI - 1];
            }
            indexI++;
        }
        return null;
    }
}