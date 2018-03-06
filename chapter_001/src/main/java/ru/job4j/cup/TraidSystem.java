package ru.job4j.cup;

import java.util.ArrayList;
import java.util.List;

public class TraidSystem<E extends Form> implements System<E> {
    List<Cup<E>> cups;

    public TraidSystem() {
        cups = new ArrayList<Cup<E>>();
    }

    private Boolean checkForId(int id) {
        Boolean isFind = true;
        for (Cup<E> cup : cups) {
            for (E request : cup.sells) {
                if (request.getId() == id) {
                    isFind = false;
                    break;
                }
            }
            if (!isFind) {
                break;
            }
        }
        for (Cup<E> cup : cups) {
            for (E request : cup.buys) {
                if (request.getId() == id) {
                    isFind = false;
                    break;
                }
            }
            if (!isFind) {
                break;
            }
        }
        return isFind;
    }



    @Override
    public boolean add(int id, String book, int action, int price, int value) {
        Boolean idFlag = checkForId(id);
        /**
         проверяем, есть ли уже заявка с таким номером,
         если есть, то мы не можем изменить ее,
         так как каждый номер - уникален
         */
        if (!idFlag) {
            return false;
        }
        Cup cup = searchBook(book);
        if (cup == null) {
            cup = new Cup(book);
            Request reqest = new Request(id, book, 1, action, price, value);
            if (reqest.getAction() <= 0) {
                cup.ask((E) reqest, book);
            } else {
                cup.bid((E) reqest, book);
            }
            cups.add(cup);
            return true;
        }
        Request reqest = new Request(id, book, 1, action, price, value);
        if (reqest.getAction() <= 0) {
            cup.ask((E) reqest, book);
        } else {
            cup.bid((E) reqest, book);
        }
        return true;
    }

    private Cup searchBook(String book) {
        Cup<E> cupResult = null;
        for (Cup<E> cup : cups) {
            for (E el : cup.buys) {
                if (book.equals(el.getBook())) {
                    cupResult = cup;
                    return cupResult;
                }
            }
        }
        for (Cup<E> cup : cups) {
            for (E el : cup.sells) {
                if (book.equals(el.getBook())) {
                    cupResult = cup;
                    return cupResult;
                }
            }
        }
        return cupResult;
    }

    @Override
    public boolean delete(int id) {
        boolean isFind = false;
        for (Cup<E> cup : cups) {
            for (E request : cup.buys) {
                if (request.getId() == id) {
                    cup.buys.remove(request);
                    isFind = true;
                    break;
                }
            }
            for (E request : cup.sells) {
                if (request.getId() == id) {
                    cup.sells.remove(request);
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }
        return isFind;
    }

    @Override
    public String toString() {
//        List<Cup<E>> innerCupContainer = new ArrayList<>(cups.size());
//        int index = 0;
//        for (Cup<E> cup : cups) {
//            Cup<E> innerCup = new Cup<>(cup.book);
//            index++;
//            for (E outerBuff : cup.requests) {
//                int summ = 0;
//                E mediumBuf = null;
//                boolean flag = false;
//                for (E innerBuf : innerCup.requests) {
//                    if (!flag) {
//                        if (innerBuf.getPrice() == outerBuff.getPrice()) {
//                            summ += outerBuff.getValue() + innerBuf.getPrice();
//                        }
//                        flag = true;
//                    }
//                    if (innerBuf.getPrice() == outerBuff.getPrice()) {
//                        summ += outerBuff.getValue();
//                    }
//                }
//                mediumBuf = (E) new Request(outerBuff.getId(), outerBuff.getBook(), outerBuff.isType(),
//                        outerBuff.getAction(), outerBuff.getPrice(), summ);
//                innerCup.requests.add(mediumBuf);
//                innerCupContainer.add(innerCup);
//            }
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Cup<E> cup : cups) {
//            for (E outerBuff : cup.requests) {
//                stringBuilder.append("[" + outerBuff.getBook() + "] [" + outerBuff.getValue() + "] [" + outerBuff.getValue() + "\n");
//            }
//        }
//
//  return stringBuilder.toString();


        StringBuilder stringBuilder = new StringBuilder();
        for (Cup<E> cup : cups) {
            stringBuilder.append(cup.toString());
        }
        return stringBuilder.toString();
    }
}