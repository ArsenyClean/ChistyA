package ru.job4j.service;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.Iterator;

/**
 * Class MatrixIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class MatrixIterator implements Iterator {

    private int [][] jaggetArray;
    public int indexI = 0, indexJ = 0;

    public MatrixIterator(final int[][] jaggetArray) {
        this.jaggetArray = jaggetArray;
    }

    @Override
    public boolean hasNext() {
        boolean result = true;
        if (jaggetArray.length <=  indexJ)
            result = false;
        return result;
    }

    @Override
    public Object next() {
        int result = jaggetArray[indexJ][indexI];
        if (jaggetArray[indexJ].length >  indexI)
            indexI++;
        if (jaggetArray[indexJ].length == indexI) {
            indexJ++;
            indexI = 0;
        }
        return result;
    }
}