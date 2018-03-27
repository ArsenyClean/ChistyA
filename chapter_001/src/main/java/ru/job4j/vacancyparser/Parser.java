package ru.job4j.vacancyparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private List<Vacancy> vacancies = new ArrayList<>();

    private String url = "http://www.sql.ru/forum/job/";

    private Pattern pattern = Pattern.compile("\\b[Jj]ava\\b");

    private static final Logger LOG = LoggerFactory.getLogger(ImportVacancyIntoDB.class);

    private Date lastDate = new Date();

    public List<Vacancy> parserStart(boolean firstStart) throws IOException {
        int i = 0;
        boolean stopDo = false;
        while (!stopDo) {
            Document document = Jsoup.connect(String.format("%s%s", url, i++)).userAgent("Mozilla").get();
            Elements elements = document.getElementsByClass("postslisttopic");
                Vacancy vacancy;
                for (Element element : elements) {
                    Element elemChild = element.child(0);
                    String text = elemChild.text();
                    String ref = elemChild.attr("href");
                Matcher matcher = pattern.matcher(text);
                Pattern pattern1 = Pattern.compile("\\b[Jj]ava [Ss]cript\\b");
                Pattern pattern2 = Pattern.compile("\\b[Jj]ava[Ss]cript\\b");
                Matcher matcher1 = pattern1.matcher(text);
                Matcher matcher2 = pattern2.matcher(text);
                while (matcher.find() && !matcher1.find() && !matcher2.find()) {
                    vacancy = this.makeVacancy(text, ref);
                    if (firstStart) {
                        if (vacancy.getCreateDate().getYear() >= new Date().getYear()) {
                            this.vacancies.add(vacancy);
                        }
                    } else {
                        if (vacancy.getCreateDate().compareTo(lastDate) >= 0) {
                            this.vacancies.add(vacancy);
                        }
                    }
                    if (vacancy.getCreateDate().getYear() < new Date().getYear() - 3) {
                        stopDo = true;
                    }
                }
            }
        }
        lastDate = new Date();
        return vacancies;
    }

    private Vacancy makeVacancy(String words, String reference) {
        Vacancy vacancy = null;
        try {
            Document document = Jsoup.connect(reference).userAgent("Mozilla").get();
            Element element = document.getElementsByClass("msgTable").first();
            Element child = element.child(0);
            String body = child.getElementsByClass("msgBody").last().text();
            Date date = this.parseStringToDate(child.getElementsByClass("msgFooter").last().text());
            vacancy = new Vacancy(words, body, date);
        } catch (IOException | ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancy;
    }

    private Date parseStringToDate(String stringWithDate) throws ParseException {
        final long day = 60 * 60 * 24 * 1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy HH:mm");
        long mill;
        String result = stringWithDate.replaceAll(",", "").split("\\[")[0];
        if (result.contains("сегодня")) {
            mill = dateMakeInMill(result.split(" ")[1]);
        } else {
            if (result.contains("вчера")) {
                mill = dateMakeInMill(result.split(" ")[1]) - day;
            } else {
                mill = simpleDateFormat.parse(result).getTime();
            }
        }
        return new Date(mill);
    }

    private long dateMakeInMill(final String s) {
        int hour;
        int minute;
        String[] tmp = s.split(":");
        hour = Integer.parseInt(tmp[0]);
        minute = Integer.parseInt(tmp[1]);
        Date currentDate = new Date();
        long date = new Date(currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDate(),
                hour, minute).getTime();
        return date;
    }
}