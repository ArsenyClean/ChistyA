package ru.job4j.service;

public class RoleStore {

    SimpleArray<Role> simple;

    public RoleStore(int size) {
        simple = new SimpleArray<Role>(size);
    }

}