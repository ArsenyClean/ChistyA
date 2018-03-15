package ru.job4j.currency.thread;

public class Threads {

    public static class Thread1 implements Runnable {
        public int thread1() {
            String string = " abc defg opq rstu vw x yz ";
            char[] chars = string.toCharArray();
            int counter = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    counter++;
                }
            }
            System.out.println("Количество пробелов: " + counter);
            return counter;
        }

        @Override
        public void run() {
            thread1();
        }
    }

    public static class Thread2 implements Runnable {
        public int thread2() {
            String string = " abc defg opq rstu vw x yz ";
            char[] chars = string.toCharArray();
            int counter = 0;
            boolean isWord = false;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != ' ' && !isWord) {
                    isWord = true;
                    counter++;
                }
                if (chars[i] == ' ' && isWord) {
                    isWord = false;
                }
            }
            System.out.println("Количество слов: " + counter);
            return counter;
        }

        @Override
        public void run() {
            thread2();
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();

    }
}