package ru.job4j.currency.thread.bomber;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BomberMan {

    public class Hero {

        private int placeLength;
        private int placeHigth;

        public int getLength() {
            return placeLength;
        }

        public int getHigth() {
            return placeHigth;
        }

        public void setLenght(int placeLength) {
            this.placeLength = placeLength;
        }

        public void setHigth(int placeHigth) {
            this.placeHigth = placeHigth;
        }

        public Hero(int boardLength, int boardHight) {
            placeLength = boardLength / 2;
            placeHigth = boardHight / 2;
        }

        public void go(int placeLength, int placeHigth) {
            this.placeHigth = placeHigth;
            this.placeLength = placeLength;
        }
    }

    private ReentrantLock[][] board;
    private int bHigth;
    private int bLength;
    private final int min = 5;
    private final int max = 7;

    public void randomBomberMan() {
        Random random = new Random();
        final int sizeLength = min + random.nextInt(max - min);
        final int sizeHigth = min + random.nextInt(max - min);
        board = new ReentrantLock[sizeLength][sizeHigth];
        bHigth = sizeHigth;
        bLength = sizeLength;
    }

    public BomberMan() {
        board = new ReentrantLock[min][min];
        bHigth = min;
        bLength = min;
    }

    public void starter() {
        Thread heroThread = new Thread() {
            @Override
            public void run() {
                Hero hero = new Hero(bLength, bHigth);
                hero.
            }
        };
    }

    private void moveHero(Hero hero) {
        boolean moveIsDone = false;
        while (!moveIsDone) {
            Random randomPlace = new Random();
            int previousPlace = 0;
            int placeOfHero = 1 + randomPlace.nextInt(4); //Рандомно ступаем героем(1 - влево, 2 - наверх, 3 - вправо, 4 - вниз)
            while (placeOfHero == previousPlace) { //Проверяем, пытались ли мы пойти в эту сторону в прошлый раз
                placeOfHero = 1 + randomPlace.nextInt(4);
            }
            previousPlace = placeOfHero;
            if (placeOfHero == 1) {     //1 - значит движение влево
                if (hero.placeLength > 0) { //Если это не крайняя левая позиция на карте
                    try {
                        if (board[hero.getLength() - 1][hero.getHigth()].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength() + 1][hero.getHigth()].unlock();
                            hero.go(hero.getLength() - 1, hero.getHigth());
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (placeOfHero == 2) { //2 - значит движение наверх
                if (hero.placeHigth > 0) {//Если это не крайняя верхняя точка
                    try {
                        if (board[hero.getLength()][hero.getHigth() - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            hero.go(hero.getLength(),hero.getHigth() - 1);
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (placeOfHero == 3) {
                if (hero.getLength() < bLength - 2) {
                    try {
                        if (board[hero.getLength() + 1][hero.getHigth()].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            hero.go(hero.getLength() + 1,hero.getHigth());
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (placeOfHero == 4) {
                if (hero.getHigth() < bHigth - 2) {
                    try {
                        if (board[hero.getLength()][hero.getHigth() + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            hero.go(hero.getLength(),hero.getHigth() + 1);
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}