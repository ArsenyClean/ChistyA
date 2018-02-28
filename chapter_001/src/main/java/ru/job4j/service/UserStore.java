package ru.job4j.service;

public class UserStore<T> implements Store{

    SimpleArray<User> userStore;

    public UserStore(int size) {
        userStore = new SimpleArray<User>(size);
    }

    @Override
    public void add(Base model) {
        userStore.add((User)model);
    }

    @Override
    public boolean replace(String id, Base model) {
        Helper<User> helpUser = new Helper<>();
        int result = helpUser.searcher(userStore, id);
        if (result < 0){
            return false;
        } else {
            userStore.objects[result] = model;
            return true;
        }
    }

    @Override
    public boolean delete(String id) {
        Helper<User> helpUser = new Helper<>();
        int result = helpUser.searcher(userStore, id);
        if (result < 0){
            return false;
        } else {
            helpUser.shifter(userStore, result);
            userStore.index--;
            return true;
        }
    }

    @Override
    public Base findById(String id) {
        Helper<User> helpUser = new Helper<>();
        int result = helpUser.searcher(userStore, id);
        if (result < 0){
            return null;
        } else {
            return (Base) userStore.objects[result];
        }
    }

    @Override
    public String toString() {
        Helper<User> helpUser = new Helper<>();
        return helpUser.toString(userStore);
    }
}