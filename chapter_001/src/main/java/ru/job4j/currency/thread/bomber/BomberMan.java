package ru.job4j.currency.thread.bomber;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BomberMan {

    public class BomberReentrantLock {

        String name;
        ReentrantLock lock;

        public BomberReentrantLock(String name) {
            this.name = name;
            lock = new ReentrantLock();
        }
    }

    public class Hero {
        private int placeLength;
        private int placeHigth;
        private String name;

        private int getLength() {
            return placeLength;
        }

        private int getHigth() {
            return placeHigth;
        }

        private String getName() {
            return this.name;
        }

        public Hero(int boardLength, int boardHight, String name) {
            this.name = name;
            boolean lockIsDone = false;
            while (!lockIsDone) {
                try {
                    generatePlace(boardLength, boardHight);
                    if (board[placeLength][placeHigth].lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                        lockIsDone = true;
                        board[placeLength][placeHigth].name = name;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

    private final BomberReentrantLock[][] board;
    private final int bHigth;
    private final int bLength;

    public BomberMan(final int lengthOrIfRandomItIsMin, final int higthOrIfRandomItIsMax, boolean randomMap) {
        if (!randomMap) {
            bLength = lengthOrIfRandomItIsMin;
            bHigth = higthOrIfRandomItIsMax;
        } else {
            bLength = generateMapeSizes(lengthOrIfRandomItIsMin, higthOrIfRandomItIsMax);
            bHigth = generateMapeSizes(lengthOrIfRandomItIsMin, higthOrIfRandomItIsMax);
        }
        board = new BomberReentrantLock[bLength][bHigth];
        for (int i = 0; i < bLength; i++) {
            for (int j = 0; j < bHigth; j++) {
                board[i][j] = new BomberReentrantLock(" ");
            }
        }
    }

    /**
     *
     * @param hero герой для перемещения
     * @param howMuchThinkToGo время на попытку залочиться
     * @param walkFromConsole если ввод через консоль, то устанавливаем true
     * @param whereToWalk если ввод консольный, то мы задаем движение через эту переменную
     *                    (1 - наверх, 2 - налево, 3 - вниз, 4 - вправо)
     */
    private void moveHero(Hero hero, long howMuchThinkToGo, boolean walkFromConsole, int whereToWalk) {
        boolean moveIsDone = false;
        int previousPlace = 0;
        while (!moveIsDone) {
            int newPlace;
            if (!walkFromConsole) {
                Random randomPlace = new Random();
                newPlace = 1 + randomPlace.nextInt(4); //Рандомно ступаем героем
                while (newPlace == previousPlace) { //Проверяем, пытались ли мы пойти в эту сторону в прошлый раз
                    newPlace = 1 + randomPlace.nextInt(4);
                }
                previousPlace = newPlace;
            } else {
                newPlace = whereToWalk;
            }
            if (newPlace == 1) {
                if (hero.getLength() > 0) { //Если это не крайняя левая позиция на карте
                    try {
                        if (board[hero.getLength() - 1][hero.getHigth()].lock.tryLock(howMuchThinkToGo, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength() - 1][hero.getHigth()].name = hero.getName();
                            board[hero.getLength()][hero.getHigth()].name = " ";
                            board[hero.getLength()][hero.getHigth()].lock.unlock();
                            hero.setPlaceOfHero(hero.getLength() - 1, hero.getHigth());
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (newPlace == 2) {
                if (hero.getHigth() > 0) { //Если это не крайняя верхняя точка
                    try {
                        if (board[hero.getLength()][hero.getHigth() - 1].lock.tryLock(howMuchThinkToGo, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth() - 1].name = hero.getName();
                            board[hero.getLength()][hero.getHigth()].name = " ";
                            board[hero.getLength()][hero.getHigth()].lock.unlock();
                            hero.setPlaceOfHero(hero.getLength(), hero.getHigth() - 1);
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (newPlace == 3) {
                if (hero.getLength() < bLength - 1) {
                    try {
                        if (board[hero.getLength() + 1][hero.getHigth()].lock.tryLock(howMuchThinkToGo, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength() + 1][hero.getHigth()].name = hero.getName();
                            board[hero.getLength()][hero.getHigth()].name = " ";
                            board[hero.getLength()][hero.getHigth()].lock.unlock();
                            hero.setPlaceOfHero(hero.getLength() + 1, hero.getHigth());
                            moveIsDone = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (newPlace == 4) {
                if (hero.getHigth() < bHigth - 1) {
                    try {
                        if (board[hero.getLength()][hero.getHigth() + 1].lock.tryLock(howMuchThinkToGo, TimeUnit.MILLISECONDS)) {
                            board[hero.getLength()][hero.getHigth() + 1].name = hero.getName();
                            board[hero.getLength()][hero.getHigth()].name = " ";
                            board[hero.getLength()][hero.getHigth()].lock.unlock();
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

    private int generateMapeSizes(int min, int max) {
        Random random = new Random();
        if (min > max) {
            int buf = min;
            min = max;
            max = buf;
        }
        int result = min + random.nextInt(max - min);
        return result;
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
                    if (board[i][j].lock.isLocked()) {
                        stringBuilder.append(board[i][j].name);
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

    public void starter(int numberOfStones, int numberOfMonsters) {
        Thread stonesThread = new Thread() {
            @Override
            public void run() {
                Hero[] stones = new Hero[numberOfStones];
                for (int i = 0; i < numberOfStones; i++) {
                    stones[i] = new Hero(bLength, bHigth, "O"); //Name для препятствий O
                }
            }
        };
        stonesThread.start(); //Генрируем камни
        try {
            stonesThread.join(); // Ждем генерации
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread heroThread = new Thread() {
            @Override
            public void run() {
                Hero hero = new Hero(bLength, bHigth, "1"); //Name для бомбермена 1
                for (int i = 0; i < 20; i++) {
                    moveHero(hero, 500, false, 3);
                    try {
                        Thread.sleep(1000);
                        System.out.println(printMap());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread monstersThread = new Thread() {
            @Override
            public void run() {
                Hero[] monsters = new Hero[numberOfMonsters];
                for (int i = 0; i < numberOfMonsters; i++) {
                    monsters[i] = new Hero(bLength, bHigth, "M"); //Name для монстров M
                }
                while (!Thread.currentThread().isInterrupted()) {
                    for (int i = 0; i < numberOfMonsters; i++) {
                        moveHero(monsters[i], 5000, false, 1);
                    }
                    try {
                        Thread.sleep(1000);
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
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println();
                    }
                }
            }
        };
        monstersThread.start();
        mapShow.start();
        heroThread.start();
        try {
            heroThread.join();
            mapShow.interrupt();
            monstersThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}