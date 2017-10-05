package ru.geekbrains.java3.lesson4.mt.p3_synch_blocks;

public class Example_SB_1 {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        Example_SB_1 e2 = new Example_SB_1();
        new Thread(() -> e1.method1()).start(); // Одновременно два потока выполнять метод не смогут, так как синхронизация идет по объекту e1
        new Thread(() -> e1.method2()).start(); // Одновременно два потока выполнять метод не смогут, так как синхронизация идет по объекту e1
    }

    public synchronized void method1() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }

    public void method3() {
        System.out.println("M3");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M4");
    }

    public synchronized void method2() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }
}
