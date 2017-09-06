package com.company;

public class Main4 {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (counter) {
                    for (int i = 0; i < 100; i++) {
                        counter.inc();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("T1: " + counter.getX());
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (counter) {
                    for (int i = 0; i < 100; i++) {
                        counter.dec();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("T2: " + counter.getX());
                    }
                }
            }
        });

        t1.start();
        t2.start();

        synchronized (counter) {
            System.out.println(counter.getX());
        }
    }
}
