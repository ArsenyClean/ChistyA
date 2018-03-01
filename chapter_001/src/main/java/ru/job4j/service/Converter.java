package ru.job4j.service;

import java.util.*;

/**
 * Class EvenNumbersIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class Converter  {
    private Iterator<Iterator<Integer>> mainIt;
    private Iterator<Integer> innerIt;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        mainIt = it;
        innerIt = mainIt.next();

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                int flag = searcher();
                while (flag == 1) {
                    innerIt = mainIt.next();
                    flag = searcher();
                }
                return flag == 0;
            }

            @Override
            public Integer next() throws NoSuchElementException {
                int flag = searcher();
                while (flag == 1) {
                    innerIt = mainIt.next();
                    flag = searcher();
                }
                if (flag == 0 || flag == -1) {
                    return innerIt.next();
                }
                throw new NoSuchElementException();
            }

            int searcher() {
                if (mainIt.hasNext()) {
                    if (innerIt.hasNext()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
                if (innerIt.hasNext()) {
                    return 0;
                }
                return -1;
            }
        };
    }
}