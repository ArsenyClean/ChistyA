package ru.job4j.vacancyparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ParserVacancyStarter {

    long timeToSleep = 15000;

    private Logger log = LoggerFactory.getLogger(VacanciesToBase.class);

    public void start() {
        int i = 0;
        log.info("Работа парсера началась, итерация номер: " + i);
        Parser parser = new Parser();
        boolean firstStart = true;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<Vacancy> list = parser.parserStart(firstStart);
                VacancyFilter vacancyFilter = new VacancyFilter();
                list = vacancyFilter.filter(list);
                VacanciesToBase v = new VacanciesToBase(list);
                v.makeBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            firstStart = false;
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            log.info("Итерация парс: " + i);
        }
        log.info("Работа парсера окончена ");
    }
}