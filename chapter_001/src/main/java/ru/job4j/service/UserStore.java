package ru.job4j.service;

import javax.jws.soap.SOAPBinding;

public class UserStore {



    SimpleArray<User> simple;

    public UserStore(int size) {
        simple = new SimpleArray<User>(size);
    }
}