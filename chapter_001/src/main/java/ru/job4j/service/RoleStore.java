package ru.job4j.service;
/**
 * RoleStore яляется хранилищем для элементов типа Role
 * @author Chisty Arseny
 * @since 28.02.2018
 */
public class RoleStore implements Store{

    SimpleArray<Role> roleStore;

    /**
     * Конструктор с инициализацией хрнилища
     */
    public RoleStore(int size) {
        roleStore = new SimpleArray<Role>(size);
    }

    /**
     * add добавляет элемент типа Base в массив
     * @param model
     */
    @Override
    public void add(Base model) {
        roleStore.add((Role)model);
    }

    /**
     * replace заменяет элемнт массива на новый
     * @param id
     * @param model
     * @return true, если элемент найден и заменен,  false, если элемент не нйден
     */
    @Override
    public boolean replace(String id, Base model) {
        Helper<Role> helpUser = null;
        int result = helpUser.searcher(roleStore, id);
        if (result < 0){
            return false;
        } else {
            roleStore.objects[result] = model;
            return true;
        }
    }

    /**
     * delete удаляет элемент из массива
     * @param id
     * @return true если элеменит найден и удален, false если элемент не найден
     */
    @Override
    public boolean delete(String id) {
        Helper<Role> helpUser = null;
        int result = helpUser.searcher(roleStore, id);
        if (result < 0){
            return false;
        } else {
            helpUser.shifter(roleStore, result);
            roleStore.index--;
            return true;
        }
    }

    /**
     * findById находит искомый элемент
     * @param id
     * @return null если элемент не нйден, Base если элемент найден
     */
    @Override
    public Base findById(String id) {
        Helper<Role> helpUser = null;
        int result = helpUser.searcher(roleStore, id);
        if (result < 0) {
            return null;
        } else {
            return (Base) roleStore.objects[result];
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