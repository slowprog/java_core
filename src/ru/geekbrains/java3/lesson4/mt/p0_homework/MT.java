package ru.geekbrains.java3.lesson4.mt.p0_homework;

public class MT {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Thread t2 = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        // System.out.println(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();

        });
        t1.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
        while (true) {

        }
    }
}
