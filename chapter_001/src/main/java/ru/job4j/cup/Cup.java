package ru.job4j.cup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс сткан, эмитация биржевого стакана
 * @param <E> тип заявки
 */
public class Cup<E extends Form>  {

    List<E> sells;
    List<E> buys;
    public String book;

    Cup(String book) {
        sells = new ArrayList<>();
        buys = new ArrayList<>();
        this.book = book;
    }

    public void bid(E request, String book) {
        this.book = book;
        buys.add(request);
        sorting();
    }

    /**
     * Метод продать ценную бумагу
     * @param request присылаем заявку с нашими данными
     * @param book а также название эммитента
     */
    public void ask(E request, String book) {
        this.book = book;
        sells.add(request);
        sorting();
    }

    /**
     * Метод сортирующий положенные ценные бумаги
     */
    public void sorting() {
        for (E buff1 : buys) {
            for (E buff2 : sells) {
                if (buff1.value != 0 && buff2.value != 0) {
                    if (buff1.price >= buff2.price) {
                        helpSorting1(buff1, buff2);
                    }
                }
            }
        }
        for (E buff1 : sells) {
            for (E buff2 : buys) {
                if (buff1.price <= buff2.price) {
                    if (buff1.value != 0 && buff2.value != 0)  {
                        helpSorting1(buff1, buff2);
                    }
                }
            }
        }
        helpSorting2(buys);
        helpSorting2(sells);
    }

    private void helpSorting1(E buff1, E buff2) {
        int result = buff1.value - buff2.value;
        if (result > 0) {
            buff2.value = 0;
            buff1.value = result;
        }
        if (result < 0) {
            buff2.value = result * (-1);
            buff1.value = 0;
        }
        if (result == 0) {
            buff1.value = 0;
            buff2.value = 0;
        }
    }

    private void helpSorting2(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).value == 0) {
                E buff = list.get(i);
                list.remove(buff);
            }
        }
        list.sort(
                new Comparator<E>() {
                    @Override
                    public int compare(E o1, E o2) {
                        Integer oo1 = o1.price;
                        Integer oo2 = o2.price;
                        return oo1.compareTo(oo2);
                    }
                }
        );
    }

    /**
     * Метож складывающий количество ценных бумаг одного эммитента с одинаковой ценой
     * @param join принимает списо либо на продажу либо на покупку
     * @return отсортированный список
     */
    private List<E> joiner(List<E> join) {
        List<E> elements = new ArrayList<>();
        for (E data : join) {
            boolean isFind = false;
            Request request = new Request(data.id, data.book, data.type,
                    data.action, data.price, data.value);
            for (E dataRequest : elements) {
                if (data.price == dataRequest.price) {
                    dataRequest.value += data.value;
                    isFind = true;
                }
            }
            if (!isFind) {
                elements.add((E) request);
            }
        }
        return elements;
    }


    @Override
    public String toString() {
        List<E> sellsJoin = joiner(sells);
        List<E> buysJoin = joiner(buys);
        StringBuilder stringBuilder = new StringBuilder();
        for (E sellData : sellsJoin) {
            stringBuilder.append("  " + sellData.value + "      " + sellData.price + "      ");
            for (E buysData : buysJoin) {
                if (sellData.price == buysData.price) {
                    stringBuilder.append(buysData.value);
                    buysData.type = 0;
                }
            }
            stringBuilder.append("\n");
        }
        for (E buysData : buysJoin) {
            if (buysData.type != 0) {
                stringBuilder.append("          " + buysData.price + "     " + buysData.value);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}