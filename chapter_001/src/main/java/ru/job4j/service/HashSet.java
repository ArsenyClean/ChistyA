package ru.job4j.service;

/**
    Class HashSet     выполнение задния 3. Реализовать коллекцию типа Set на базе хэш-таблицы [#998]
    @ author Chisty Arseny
    @ since 23/02/2018
 */

public class HashSet<E> {

    Object[] users;
    int hashConst = 16;
    int tableFull = 0;

    /**
     * Конструктор инициализирующий массив
     * @param users
     */
    public HashSet(Object[] users) {
        this.users = users;
    }

    /**
     * Метод add, добавляющий элемент в хэш-таблицу
     * @param e
     * @return
     */
    boolean add(E e) {
        int index = hashMake(e);
        while (true) {
            if (index < users.length) {
                while (users[index] == null) {
                    users[index]= e;
                    tableFull++;
                    double coeff= ((double)tableFull)/(double)users.length;
                    if (coeff > 0.75) {
                        users = growTable();
                        break;
                    }
                    return true;
                }
                index++;
            }
            else
                return false;
        }
    }

    /**
     * Метод удваивающий хэш-таблицу
     * @return
     */
    Object[] growTable() {
        Object[] usersCop = new Object[users.length*2];
        for ( int i = 0; i < users.length; i++ ) {
            usersCop[i] = users[i];
        }
        return usersCop;
    }

    /**
     * Метод, проверяющий, содержит ли хэш-таблица элемент е
     * @param e
     * @return
     */
    boolean contains(E e) {
        int index = hashMake(e);
        while (true) {
            if (index < users.length) {
                while (users[index] != null) {
                    if (users[index] == e) {
                        index++;
                        return true;
                    }
                    index++;
                }
                index++;
            }
            else
                return false;
        }
    }

    /**
     * Метод, удаляющий элемент е
     * @param e
     * @return
     */
    boolean remove(E e) {
        int index = hashMake(e);
        int flagEnd = index;
        boolean endFound = false;
        while (true) {
            if (index < users.length) {
                while (users[index] != null) {
                    if (users[index] == e) {
                        flagEnd++;
                        endFound = true;
                    }
                    index++;
                    if (index < users.length)
                        if (users[index] != e) {
                            flagEnd--;
                            break;
                        }
                    if (index >= users.length) {
                        flagEnd--;
                        break;
                    }
                }
                if (endFound) {
                    users[flagEnd] = null;
                    return true;
                }
                index++;
            }
            else
                return false;
        }
    }

    /**
     * Метод, вычисляющий хэш-функцию
     * @param value
     * @return
     */
    int hashMake(E value) {
        return (((Integer) value) * hashConst) % users.length;
    }
}