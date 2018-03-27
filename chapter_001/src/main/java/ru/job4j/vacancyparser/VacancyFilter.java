package ru.job4j.vacancyparser;

import java.util.List;

public class VacancyFilter {

    public List<Vacancy> filter(List<Vacancy> list) {
        for (int i = 0; i < list.size(); i++) {
            String buff = list.get(i).text;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).text.equals(buff)) {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}