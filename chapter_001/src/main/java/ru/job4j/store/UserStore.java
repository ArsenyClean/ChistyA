package ru.job4j.store;

/**
 * UserStore яляется хранилищем для элементов типа User
 * @author Chisty Arseny
 * @since 28.02.2018
 */
public class UserStore<T extends Base> implements Store<T> {

    private SimpleArray<T> data;
    /**
     * Конструктор с инициализацией хрнилища
     */
    public UserStore(int size) {
        data = new SimpleArray<>(size);
    }

    /**
     * add добавляет элемент типа Base в массив
     * @param model добавляемый элемент
     */
    @Override
    public void add(T model) {
        data.add(model);
    }

    /**
     * replace заменяет элемнт массива на новый
     * @param id значение заменяемого элемента
     * @param model новый элеент для замены
     * @return true, если элемент найден и заменен,  false, если элемент не нйден
     */
    @Override
    public boolean replace(String id, Base model) {
        Helper<T> helpUser = new Helper<>();
        int result = helpUser.searcher(data, id);
        if (result < 0) {
            return false;
        } else {
            data.objects[result] = model;
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
        Helper<T> helpUser = new Helper<>();
        int result = helpUser.searcher(data, id);
        if (result < 0) {
            return false;
        } else {
            boolean resultShift = helpUser.shifter(data, result);
            data.index--;
            return resultShift;
        }
    }

    /**
     * findById находит искомый элемент
     * @param id значение искомого элемента
     * @return null если элемент не нйден, Base если элемент найден
     */
    @Override
    public T findById(String id) {
        Helper<T> helpUser = new Helper<>();
        int result = helpUser.searcher(data, id);
        if (result < 0) {
            return null;
        } else {
            return (T) data.objects[result];
        }
    }
    /**
     * toString возвращает строковой эквивалент массива
     * @return String
     */
    @Override
    public String toString() {
        Helper<T> helpUser = new Helper<>();
        return helpUser.toString(data);
    }
}