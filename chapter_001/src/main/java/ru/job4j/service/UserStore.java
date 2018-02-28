package ru.job4j.service;

/**
 * UserStore яляется хранилищем для элементов типа User
 * @author Chisty Arseny
 * @since 28.02.2018
 */
public class UserStore implements Store {

    private SimpleArray<User> userStore;

    /**
     * Конструктор с инициализацией хрнилища
     */
    public UserStore(int size) {
        userStore = new SimpleArray<>(size);
    }

    /**
     * add добавляет элемент типа Base в массив
     * @param model добавляемый элемент
     */
    @Override
    public void add(Base model) {
        userStore.add((User) model);
    }

    /**
     * replace заменяет элемнт массива на новый
     * @param id значение заменяемого элемента
     * @param model новый элеент для замены
     * @return true, если элемент найден и заменен,  false, если элемент не нйден
     */
    @Override
    public boolean replace(String id, Base model) {
        Helper<User> helpUser = new Helper<>();
        int result = helpUser.searcher(userStore, id);
        if (result < 0) {
            return false;
        } else {
            userStore.objects[result] = model;
            return true;
        }
    }

    /**
     * delete удаляет элемент из массива
     * @param id значение удаляемого элемента
     * @return true если элеменит найден и удален, false если элемент не найден
     */
    @Override
    public boolean delete(String id) {
        Helper<User> helpUser = new Helper<>();
        int result = helpUser.searcher(userStore, id);
        if (result < 0) {
            return false;
        } else {
            boolean resultShift = helpUser.shifter(userStore, result);
            userStore.index--;
            return resultShift;
        }
    }

    /**
     * findById находит искомый элемент
     * @param id значение искомого элемента
     * @return null если элемент не нйден, Base если элемент найден
     */
    @Override
    public Base findById(String id) {
        Helper<User> helpUser = new Helper<>();
        int result = helpUser.searcher(userStore, id);
        if (result < 0) {
            return null;
        } else {
            return userStore.objects[result];
        }
    }
    /**
     * toString возвращает строковой эквивалент массива
     * @return String
     */
    @Override
    public String toString() {
        Helper<User> helpUser = new Helper<>();
        return helpUser.toString(userStore);
    }
}