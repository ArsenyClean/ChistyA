package ru.job4j.sql;

import javax.xml.bind.annotation.XmlElement;

public  class Entry {
    @XmlElement(name = "field")
    int field;

    public void setField(int field) {
        this.field = field;
    }
}