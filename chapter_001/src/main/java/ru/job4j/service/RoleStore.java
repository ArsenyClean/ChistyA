package ru.job4j.service;

public class RoleStore implements Store{

    SimpleArray<Role> roleStore;

    public RoleStore(int size) {
        roleStore = new SimpleArray<Role>(size);
    }

    @Override
    public void add(Base model) {
        roleStore.add((Role)model);
    }

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

    @Override
    public String toString() {
        Helper<Role> helpUser = new Helper<>();
        return helpUser.toString(roleStore);
    }
}