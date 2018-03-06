package ru.job4j.cup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cup<E extends Form>  {

    List<E> sells;
    List<E> buys;
    public String book;

    Cup(String book) {
        sells = new ArrayList<>();
        buys = new ArrayList<>();
        this.book = book;
    }

    public boolean bid(E request, String book) {
        boolean flagIsDone = false;
        this.book = book;
        for (E buffRequest : sells) {
            if (request.getPrice() <= buffRequest.getPrice()) {
                flagIsDone = scener(request, buffRequest, buys, sells);
            }
        }
        if (!flagIsDone) {
            buys.add(request);
            flagIsDone = true;
        }
        sells.sort(
                new Comparator<E>() {
                    @Override
                    public int compare(E o1, E o2) {
                        Integer oo1 = o1.getPrice();
                        Integer oo2 = o2.getPrice();
                        return oo2.compareTo(oo1);
                    }
                }
        );
        return flagIsDone;
    }

    public boolean ask(E request, String book) {
        boolean flagIsDone = false;
        this.book = book;
        for (E buffRequest : buys) {
            if (request.getPrice() >= buffRequest.getPrice()) {
                flagIsDone = scener(request, buffRequest, sells, buys);
            }
        }
        if (!flagIsDone) {
            sells.add(request);
            flagIsDone = true;
        }
        sells.sort(
                new Comparator<E>() {
                    @Override
                    public int compare(E o1, E o2) {
                        Integer oo1 = o1.getPrice();
                        Integer oo2 = o2.getPrice();
                        return oo2.compareTo(oo1);
                    }
                }
        );
        return flagIsDone;
    }

    private boolean scener(E request, E buffRequest, List<E> sells, List<E> buys) {
        boolean flagIsDone = false;
        int result = request.getValue() - buffRequest.getValue();
        if (result < 0) {
            E newRequest = buffRequest;
            buffRequest = (E) new Request(newRequest.getId(), newRequest.getBook(), newRequest.isType(),
                    newRequest.getAction(), newRequest.getPrice(), result * (-1));
        }
        if (result > 0) {
            E newRequest = request;
            buys.remove(buffRequest);
            sells.add((E) new Request(newRequest.getId(), newRequest.getBook(), newRequest.isType(),
                    newRequest.getAction(), newRequest.getPrice(), result));
            flagIsDone = true;
        }
        if (result == 0) {
            buys.remove(buffRequest);
            flagIsDone = true;
        }
        return flagIsDone;
    }

    public List<E> joiner(List<E> join) {
        List<E> elements = new ArrayList<>();
        for (E data : join) {
            Request request = new Request(data.getId(), data.getBook(), data.isType(),
                    data.getAction(), data.getPrice(), data.getValue());
            for (E elData : join) {
                if (data.getPrice() == elData.getPrice()) {
                    request = new Request(data.getId(), data.getBook(), data.isType(),
                            data.getAction(), data.getPrice(), data.getValue() + elData.getValue());
                }
            }
            elements.add((E) request);
        }
        return elements;
    }

    @Override
    public String toString() {
        List<E> sellsJoin = joiner(sells);
        List<E> buysJoin = joiner(buys);
        StringBuilder stringBuilder = new StringBuilder();
        for (E sellData : sellsJoin) {
            stringBuilder.append(sellData.getValue() + " " + sellData.getPrice() + " ");
            for (E buysData : buysJoin) {
                if (sellData.getPrice() == buysData.getPrice()) {
                    stringBuilder.append(buysData.getValue());
                }
            }
        }
        return stringBuilder.toString();
    }
}