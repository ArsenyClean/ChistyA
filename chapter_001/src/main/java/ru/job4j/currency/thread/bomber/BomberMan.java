package ru.job4j.currency.thread.bomber;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BomberMan {

    /**
     * Класс BomberReentrantLock
     * Данный редставляет собой альтернативу обычному элементу ReentrantLock() массив из которых
     * предлагалось сделать в условиях заданий
     * Проблема, которую я хотел решить благодаря этому, это присваивание каждому локу имени
     * Благодаря этому карта состоит не просто из локов, а каждому из них можно присвоить имя
     * и так по имени можно определить кто залочил данный элемент из массива  BomberReentrantLock, сам герой
     * препятствие, или монстр
     */
    public class BomberReentrantLock {

        String name;
        ReentrantLock lock;

        public BomberReentrantLock(String name) {
            this.name = name;
            lock = new ReentrantLock();
        }
    }

    /**
     *Класс Hero нужен для залочивания позиции  BomberReentrantLock
     * Этот класс может реализовывать не только герой, но и монстр и препятствие
     */
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

        /**
         * Метод Hero
         * С ним пытаемся "посадить" героя, монстра или препятствие на карту
         * Пока не залочится ячейка им, мы будем генерировать рандомные места на карте
         * @param boardLength длинна карты
         * @param boardHight  ширина
         * @param name имя
         */
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

        /**
         * Метод generatePlace генерирует место на карте для героя, монстра или препятствия
         * @param boardLength длинна карты
         * @param boardHight ширина
         */
        private void generatePlace(int boardLength, int boardHight) {
            Random random = new Random();
            int gener = random.nextInt(boardLength);
            placeLength = gener;
            gener = random.nextInt(boardHight);
            placeHigth = gener;
        }

        /**
         * Метод установки координат
         * @param placeLength
         * @param placeHigth
         */
        public void setPlaceOfHero(int placeLength, int placeHigth) {
            this.placeHigth = placeHigth;
            this.placeLength = placeLength;
        }
    }

    private final BomberReentrantLock[][] board;
    private final int bHigth;
    private final int bLength;

    /**
     * Конструктор BomberMan
     * Данный конструктор принимает параметры длинны и ширины BomberReentrantLock
     * Есть два режима этого конструктора:
     * 1) Если мы запускаем НЕ рандомную гнерацию карты, в таком случае первые два параметра являются
     * высотой и шириной карты, которую мы задаем
     * 2) Если мы запускаем рандомную генерацию карты, и в таком случае первый параметр является
     * МИНИМАЛЬНОЙ шириной и высотой карты, а второй параметр является МАКСИМАЛЬНОЙ шириной и высотой
     * @param lengthOrIfRandomItIsMin парметр1
     * @param higthOrIfRandomItIsMax параметр2
     * @param randomMap true означает, что работает рандомный режим генерации карты
     */
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
     * Метод для генерации размеров карты
     * @param min минимальный
     * @param max максимальный
     * @return результат
     */
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

    /**
     * Метод moveHero
     * Используется для перемещения монстров или героя
     * Выполняет цикл по условию moveISDone до тех пор пока для данного героя или монстра не найдется свободная ячейка
     * Вначале цикла, если у нас режим перемещния героя НЕ через консоль, то мы генирируем новое место до тех пор
     * пока не найдем свободную ячейку, а  если у нас режим перемещения через консоль,
     * то просто перемещаем героя в данную клетку
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

    /**
     * Метод эквивалентный toString
     * Выводит карту
     */
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

    /**
     * Метод starter
     * Сначала создает препятствия на карте
     * Далее генерируем героя и сажаем на карту
     * Потом монстров
     * @param numberOfStones
     * @param numberOfMonsters
     */
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
                    for (int i = 0; i < numberOfMonsters; i++) {    //Монстрам даем 5 секунд на раздумие куда пойти
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