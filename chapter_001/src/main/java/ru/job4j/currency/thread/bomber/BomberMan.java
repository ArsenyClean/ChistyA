package ru.job4j.currency.thread.bomber;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BomberMan {

    public class Hero {
        private int placeLength;
        private int placeHigth;
        private final int id;

        private int getLength() {
            return placeLength;
        }

        private int getHigth() {
            return placeHigth;
        }

        private int getId() {
            return this.id;
        }

        public Hero(int boardLength, int boardHight, int id) {
            this.id = id;
            generatePlace(boardLength, boardHight);
            boolean lockIsDone = false;
            while (!lockIsDone) {
                try {
                    if (board[placeLength][placeHigth].tryLock(500, TimeUnit.MILLISECONDS)) {
                        lockIsDone = true;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                generatePlace(boardLength, boardHight);
            }
            synchronized (mapMassive) {
                mapMassive[placeLength][placeHigth] = id;
            }
        }

        private void generatePlace(int boardLength, int boardHight) {
            Random random = new Random();
            int gener = random.nextInt(boardLength);
            placeLength = gener;
            gener = random.nextInt(boardHight);
            placeHigth = gener;
        }

        public void setPlaceOfHero(int placeLength, int placeHigth) {
            this.placeHigth = placeHigth;
            this.placeLength = placeLength;
        }
    }

    private final ReentrantLock[][] board;
    private volatile Integer[][] mapMassive;
    private final int bHigth;
    private final int bLength;

    public BomberMan(final int length, final int higth) {
        bLength = length;
        bHigth = higth;
        board = new ReentrantLock[bLength][bHigth];
        mapMassive = new Integer[bLength][bHigth];
        for (int i = 0; i < bLength; i++) {
            for (int j = 0; j < bHigth; j++) {
                board[i][j] = new ReentrantLock();
                mapMassive[i][j] = 0;
            }
        }
    }

    private void moveHero(Hero hero) {
        boolean moveIsDone = false;
        int previousPlace = 0;
        while (!moveIsDone) {
            Random randomPlace = new Random();
            int newPlace = 1 + randomPlace.nextInt(4); //Рандомно ступаем героем(1 - влево, 2 - наверх, 3 - вправо, 4 - вниз)
            while (newPlace == previousPlace) { //Проверяем, пытались ли мы пойти в эту сторону в прошлый раз
                newPlace = 1 + randomPlace.nextInt(4);
            }
            previousPlace = newPlace;
            if (newPlace == 1) {     //1 - значит движение влево
                if (hero.getLength() > 0) { //Если это не крайняя левая позиция на карте
                    try {
                        if (board[hero.getLength() - 1][hero.getHigth()].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            synchronized (this) {
                                mapMassive[hero.getLength() - 1][hero.getHigth()] = hero.getId();
                                mapMassive[hero.getLength()][hero.getHigth()] = 0;
                            }
                            hero.setPlaceOfHero(hero.getLength() - 1, hero.getHigth());
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (newPlace == 2) { //2 - значит движение наверх
                if (hero.getHigth() > 0) { //Если это не крайняя верхняя точка
                    try {
                        if (board[hero.getLength()][hero.getHigth() - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            synchronized (this) {
                                mapMassive[hero.getLength()][hero.getHigth() - 1] = hero.getId();
                                mapMassive[hero.getLength()][hero.getHigth()] = 0;
                            }
                            hero.setPlaceOfHero(hero.getLength(), hero.getHigth() - 1);
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (newPlace == 3) {
                if (hero.getLength() < bLength - 2) {
                    try {
                        if (board[hero.getLength() + 1][hero.getHigth()].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            synchronized (this) {
                                mapMassive[hero.getLength() + 1][hero.getHigth()] = hero.getId();
                                mapMassive[hero.getLength()][hero.getHigth()] = 0;
                            }
                            hero.setPlaceOfHero(hero.getLength() + 1, hero.getHigth());
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (newPlace == 4) {
                if (hero.getHigth() < bHigth - 2) {
                    try {
                        if (board[hero.getLength()][hero.getHigth() + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth()].unlock();
                            synchronized (this) {
                                mapMassive[hero.getLength()][hero.getHigth() + 1] = hero.getId();
                                mapMassive[hero.getLength()][hero.getHigth()] = 0;
                            }
                            hero.setPlaceOfHero(hero.getLength(), hero.getHigth() + 1);
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String printMap() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-");
        for (int i = 0; i < bHigth; i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append("-\n");
        synchronized (this) {
            for (int i = 0; i < bLength; i++) {
                stringBuilder.append("|");
                for (int j = 0; j < bHigth; j++) {
                    if (mapMassive[i][j] > 0) {
                        stringBuilder.append(mapMassive[i][j]);
                    } else {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append("|");
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append("-");
        for (int i = 0; i < bHigth; i++) {
            stringBuilder.append("-");
        }
        stringBuilder.append("-");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public void starter() {
        Thread heroThread = new Thread() {
            @Override
            public void run() {
                Hero hero = new Hero(bLength, bHigth, 1); //id для бомбермена только 1
                for (int i = 0; i < 20; i++) {
                    moveHero(hero);
                    try {
                        Thread.sleep(1000);
                        System.out.println(printMap());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread mapShow = new Thread() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    printMap();
                    try {
                        Thread.sleep(1000);
                        System.out.println(printMap());
                    } catch (InterruptedException e) {
                        System.out.println();
                    }
                }
            }
        };
        mapShow.start();
        heroThread.start();
        try {
            heroThread.join();
            mapShow.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}