package ru.job4j.service;
/**
 * RoleStore яляется хранилищем для элементов типа Role
 * @author Chisty Arseny
 * @since 28.02.2018
 */
public class RoleStore implements Store {


    private SimpleArray<Role> roleStore;

    /**
     * Конструктор с инициализацией хрнилища
     */
    public RoleStore(int size) {
        roleStore = new SimpleArray<>(size);
    }


    /**
     * add добавляет элемент типа Base в массив
     * @param model вставляемый в массив элемент
     */
    @Override
    public void add(Base model) {
        roleStore.add((Role) model);
    }

    /**
     * replace заменяет элемнт массива на новый
     * @param id значение элемета который надо заменить
     * @param model новое значение для замены
     * @return true, если элемент найден и заменен,  false, если элемент не нйден
     */
    @Override
    public boolean replace(String id, Base model) {
        Helper<Role> helpUser = new Helper<>();
        int result = helpUser.searcher(roleStore, id);
        if (result < 0) {
            return false;
        } else {
            roleStore.objects[result] = model;
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
        Helper<Role> helpUser = new Helper<>();
        int result = helpUser.searcher(roleStore, id);
        if (result < 0) {
            return false;
        } else {
            boolean resultShift = helpUser.shifter(roleStore, result);
            roleStore.index--;
            return resultShift;
        }
    }

    /**
     * findById находит искомый элемент
     * @param id индекс искомого элемента
     * @return null если элемент не нйден, Base если элемент найден
     */
    @Override
    public Base findById(String id) {
        Helper<Role> helpUser = new Helper<>();
        int result = helpUser.searcher(roleStore, id);
        if (result < 0) {
            return null;
        } else {
            return roleStore.objects[result];
        }
    }

    /**
     * toString возвращает строковой эквивалент массива
     * @return String
     */
    @Override
    public String toString() {
        Helper<Role> helpUser = new Helper<>();
        return helpUser.toString(roleStore);
    }
}