package ru.job4j.vacancyparser;

import java.util.Date;

public class Vacancy {

    Date date;
    String text;
    String body;

    public Vacancy(String text, String body, Date date) {
        this.date = date;
        this.text = text;
        this.body = body;
    }

    public Date getCreateDate() {
        return date;
    }
}