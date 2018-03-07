package ru.job4j.cup;

/**
 * Класс для заявок
 */
public class Request extends Form {
    Request(int id, String book, int type, int action, int price, int value) {
        super(id, book, type, action, price, value);
    }
}
