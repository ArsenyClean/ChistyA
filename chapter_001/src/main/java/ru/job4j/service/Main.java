package ru.job4j.service;

import java.util.ArrayList;
import java.util.Comparator;

public class Main implements Comparable<Integer> {

    public static void main(String[] args) {
        Object[] y;

        ArrayList<Integer> j = new ArrayList<Integer>();

        j.add(7);
        j.add(4);
        j.add(9);

        j.sort(
                new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Integer a = (Integer) o1;
                        Integer b = (Integer) o2;
                        return a.compareTo(b);
                    }
                }
        );

        System.out.println(j);

    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }
}
