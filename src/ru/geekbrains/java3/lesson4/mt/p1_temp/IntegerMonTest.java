package ru.geekbrains.java3.lesson4.mt.p1_temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class IntegerMonTest {
    private static Integer n = new Integer(300);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("X");
                    //System.out.println(n);
                    n++;
                    //System.out.println(n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("X");
                    //System.out.println(n);
                    n++;
                    //System.out.println(n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("X");
                    //System.out.println(n);
                    n++;
                    //System.out.println(n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
