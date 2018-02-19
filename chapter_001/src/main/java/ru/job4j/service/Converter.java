package ru.job4j.service;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Class EvenNumbersIterator, решение задачи 5.1.1.
 * @author Chisty Arseny
 * @since 16.02.2018
 */

public class Converter  {
    Iterator<Iterator<Integer>> mainIt;
    Iterator<Integer> innerIt;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        mainIt = it;
        innerIt = mainIt.next();


        Iterator<Integer> r = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                while (mainIt.hasNext()) {
                    if (innerIt.hasNext())
                        return true;
                    else {
                        innerIt = mainIt.next();
                    }
                }
                if (innerIt.hasNext())
                    return true;
                return false;
            }

            @Override
            public Integer next() {
                while (mainIt.hasNext()){
                    if (innerIt.hasNext()){
                        return innerIt.next();
                    }
                    else {
                        innerIt = mainIt.next();
                    }
                }
                    if (innerIt.hasNext())
                        while (innerIt.hasNext())
                            return innerIt.next();

                return innerIt.next();
            }
        };
        return r;
    }
}