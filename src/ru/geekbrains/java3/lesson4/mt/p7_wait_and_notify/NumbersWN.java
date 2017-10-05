package ru.geekbrains.java3.lesson4.mt.p7_wait_and_notify;

public class NumbersWN {
    private static Object mon = new Object();
    private static int printed = 1;

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            final int w = i;
            new Thread(() -> {
                synchronized (mon) {
                    try {
                        while (printed != w)
                            mon.wait();
                        printed++;
                        System.out.println(w);
                        mon.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
