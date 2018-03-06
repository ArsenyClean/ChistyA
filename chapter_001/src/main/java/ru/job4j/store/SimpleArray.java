package ru.job4j.store;

/**
 * @author Chisty Arseny
 * @since 19.02.2018
 * @param <T>
 */
class SimpleArray<T extends Base> {

    Base[] objects;
    int index = 0;

    /**
     * Конструктор, инициализирует массив и задает его длинну
     * @param size размер массива
     */
    public SimpleArray(int size) {
        this.objects = new Base[size];
    }

    /**
     * add добавляет элемент в массив
     * @param model добавляемый элемент
     */
    void add(T model) {
        this.objects[index] = model;
        index++;
    }
}