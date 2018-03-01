package ru.job4j.service;

/**
    Class HashSet     выполнение задния 3. Реализовать коллекцию типа Set на базе хэш-таблицы [#998]
    @ author Chisty Arseny
    @ since 23/02/2018
 */

public class HashSet<E> {

    private Object[] users;
    private int tableFull = 0;

    /**
     * Конструктор инициализирующий массив
     * @param users принимает массив
     */
    public HashSet(Object[] users) {
        this.users = users;
    }

    /**
     * Метод add, добавляющий элемент в хэш-таблицу
     * @param e принимает элемент для добавления
     * @return true, если опирация выполнилась, и false, если не выполнилась
     */
    public boolean add(E e) {
        int index = hashCode(e);
        if (users[index] == null) {
            users[index] = e;
            tableFull++;
            double coeff = ((double) tableFull) / (double) users.length;
            if (coeff > 0.75) {
                users = growTable();
            }
            return true;
        }
        return false;
    }

    /**
     * Метод удваивающий хэш-таблицу
     * @return увеличенный массив
     */
    public Object[] growTable() {
        Object[] usersCopy = new Object[users.length * 2];
        HashSet newSet = new HashSet(usersCopy);
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                newSet.add(users[i]);
            }
        }
        return newSet.users;
    }

    /**
     * Метод, проверяющий, содержит ли хэш-таблица элемент е
     * @param e проверяемый элемент
     * @return true, если опирация выполнилась, и false, если не выполнилась
     */
    public boolean contains(E e) {
        int index = hashCode(e);
        if (users[index] != null) {
            return users[index].equals(e);
        }
        return false;
    }

    /**
     * Метод, удаляющий элемент е
     * @param e удаляемый элемент
     * @return true, если опирация выполнилась, и false, если не выполнилась
     */
    public boolean remove(E e) {
        int index = hashCode(e);
        if (users[index] == null) {
            return false;
        } else {
            users[index] = null;
            return true;
        }
    }

    /**
     * Метод, вычисляющий хэш-функцию
     * @param value принимает значение для вычисления хэш-кода
     * @return возвращает вычисленный хэш-код
     */
    public int hashCode(E value) {
        int hashConst = 31;
        int result = (value.hashCode() * hashConst) % users.length;
        if (result >= 0) {
            return result;
        }
        return ((value.hashCode() & 0x7fffffff)) % users.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < users.length; i++) {
            String string = "[" + i + "]=" + users[i] + " ";
            builder.append(string);
        }
        return builder.toString();
    }
}