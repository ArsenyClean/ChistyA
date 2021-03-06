package ru.job4j.cup;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий систему трейдинга
 * @param <E> принимает только параметры заявок(Request), унаследованных от абстрактного класса Форма (Form)
 * Реализует интерфейс System
 * @author Chisty Arseny
 * @since 7.3.2018
 */
public class TraidSystem<E extends Form> implements System {
    private List<Cup<E>> cups;

    public TraidSystem() {
        cups = new ArrayList<>();
    }

    /**
     * Метод проверяет, есть ли в системе заявки с таким же Id
     * @param id id
     * @return выполнена операция или нет
     */
    private Boolean checkForId(int id) {
        Boolean isFind = false;
        for (Cup<E> cup : cups) {
            for (E request : cup.sells) {
                if (request.id == id) {
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }
        for (Cup<E> cup : cups) {
            for (E request : cup.buys) {
                if (request.id == id) {
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }
        return isFind;
    }

    /**
     * Метод ищет стакан с искомым эммицентом и возвращает ссылку на него
     * @param book имя эммицента
     * @return возвращает ссылку на него
     */
    private Cup<E> searchBook(String book) {
        Cup<E> cupResult = null;
        for (Cup<E> cup : cups) {
            for (E el : cup.buys) {
                if (book.equals(el.book)) {
                    cupResult = cup;
                    return cupResult;
                }
            }
        }
        for (Cup<E> cup : cups) {
            for (E el : cup.sells) {
                if (book.equals(el.book)) {
                    cupResult = cup;
                    return cupResult;
                }
            }
        }
        return cupResult;
    }

    /**
     * Метод добавления заявки
     * @param id допустимо вводить любое целове число
     * @param book допустимо вводить любую строку
     * @param action Параметр действия, если хотим купить ценную бумагу, ввдим число меньше 0, если хотим продать, вводим число
     *               больше 0
     * @param price Цена ценной бумагит
     * @param value Количество ценных бумаг
     * @return возвращает успешно или нет выполнена операция
     */
    @Override
    public boolean add(int id, String book, int action, int price, int value) {
        Boolean idIsFound = checkForId(id);
        if (!idIsFound) {
            Cup cup = searchBook(book); //Если стакана с таким эммитентом не найдено, то добавляем стакан с ним
            if (cup == null) {
                cup = new Cup(book);
                Request request = new Request(id, book, 1, action, price, value);
                if (request.action <= 0) {
                    cup.bid(request, book);
                } else {
                    cup.ask(request, book);
                }
                cups.add(cup);
                idIsFound = true;
            } else {
                Request request = new Request(id, book, 1, action, price, value);
                if (request.action <= 0) {
                    cup.bid(request, book);
                } else {
                    cup.ask(request, book);
                }
                idIsFound = true;
            }
        }
        return idIsFound;
    }

    /**
     * Удаление заявки, происходит только по id
     * @param id искомая заявка
     * @return возвращает успешно или нет проведена операция
     */
    @Override
    public boolean delete(int id) {
        boolean isFind = false;
        for (Cup<E> cup : cups) {
            for (int i = 0; i < cup.buys.size(); i++) {
                if (cup.buys.get(i).id == id) {
                    E el = cup.buys.get(i);
                    cup.buys.remove(el);
                    isFind = true;
                }
                if (isFind) {
                    break;
                }
            }
            if (isFind) {
                break;
            }
            for (int i = 0; i < cup.sells.size(); i++) {
                if (cup.sells.get(i).id == id) {
                    E el = cup.sells.get(i);
                    cup.sells.remove(el);
                    isFind = true;
                }
                if (isFind) {
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }
        return isFind;
    }

    /**
     * Метод выводит информацию о заявках
     * @return строка
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Продажи  Цена  Покупки\n");
        for (Cup<E> cup : cups) {
            stringBuilder.append(cup.toString());
        }
        return stringBuilder.toString();
    }
}