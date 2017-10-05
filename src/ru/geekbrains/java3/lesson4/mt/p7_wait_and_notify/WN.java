package ru.geekbrains.java3.lesson4.mt.p7_wait_and_notify;

import java.util.ArrayList;

public class WN {
    static Object mon = new Object();
    static char currentChar = 'A';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (mon) {
                        while (currentChar != 'A') {
                            mon.wait();
                        }
                        System.out.print("A");
                        currentChar = 'B';
                        mon.notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (mon) {
                        while (currentChar != 'B') {
                            mon.wait();
                        }
                        System.out.print("B");
                        currentChar = 'A';
                        mon.notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
