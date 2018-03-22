package ru.job4j.sql;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entries")
public  class Entries {

    @XmlElement(name = "entry")
    List<Entry> entries;

    public void setField(List<Entry> entries) {
        this.entries = entries;
    }
}