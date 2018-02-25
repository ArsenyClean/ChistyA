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
        boolean endFlag = false;

        Iterator<Integer> iterator = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                int flag = searcher();
                while (flag == 1){
                    innerIt = mainIt.next();
                    flag = searcher();
                }
                if (flag == 0){
                    return true;
                }else{
                    return false;
                }
            }

            @Override
            public Integer next() throws NoSuchElementException{
                int flag = searcher();
                while (flag == 1){
                    innerIt = mainIt.next();
                    flag = searcher();
                }
                if (flag == 0 || flag == -1){
                    return innerIt.next();
                }
                throw new NoSuchElementException();
            }

            public int searcher(){
                while (mainIt.hasNext()){
                    if (innerIt.hasNext()){
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
                while (innerIt.hasNext()) {
                    return 0;
                }
                return -1;
            }
        };
        return iterator;
    }
}