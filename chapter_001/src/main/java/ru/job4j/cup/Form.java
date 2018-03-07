package ru.job4j.cup;

/**
 * Абстрактный класс для заявок
 */
public abstract class Form {

    public  int id;
    public  String book;
    public  int type;
    public  int action;
    public  int price;
    public  int value;

    Form(final int id, final String book, final int type, final int action, final int price, final int value) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.value = value;
    }
}