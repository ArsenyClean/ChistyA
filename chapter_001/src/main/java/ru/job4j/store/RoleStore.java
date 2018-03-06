package ru.job4j.store;
/**
 * RoleStore яляется хранилищем для элементов типа Role
 * @author Chisty Arseny
 * @since 28.02.2018
 */
public class RoleStore<T extends Base> implements Store<T> {

    private SimpleArray<T> data;

    /**
     * Конструктор с инициализацией хрнилища
     */
    public RoleStore(int size) {
        data = new SimpleArray(size);
    }


    /**
     * add добавляет элемент типа Base в массив
     * @param model вставляемый в массив элемент
     */
    @Override
    public void add(Base model) {
        data.add((T) model);
    }

    /**
     * replace заменяет элемнт массива на новый
     * @param id значение элемета который надо заменить
     * @param model новое значение для замены
     * @return true, если элемент найден и заменен,  false, если элемент не нйден
     */
    @Override
    public boolean replace(String id, T model) {
        Helper<T> helpUser = new Helper<T>();
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
     * @param id значение элемента для удаления
     * @return true если элеменит найден и удален, false если элемент не найден
     */
    @Override
    public boolean delete(String id) {
        Helper<T> helpUser = new Helper<T>();
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
     * @param id индекс искомого элемента
     * @return null если элемент не нйден, Base если элемент найден
     */
    @Override
    public T findById(String id) {
        Helper<T> helpUser = new Helper<T>();
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