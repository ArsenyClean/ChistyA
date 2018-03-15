package ru.job4j.mapservice;

import java.util.Calendar;

public class User {

    public String name;
    int children;
    Calendar birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }

    @Override
    public int hashCode() {
        int hash = (name != null) ? name.hashCode() : 0;
        hash += children;
        hash += (birthday != null) ? birthday.hashCode() : 0;
        return hash;
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}