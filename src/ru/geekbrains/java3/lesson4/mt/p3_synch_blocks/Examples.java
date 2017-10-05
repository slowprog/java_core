package ru.geekbrains.java3.lesson4.mt.p3_synch_blocks;

public class Examples {
    public static void main(String[] args) {
        queueTest();
//        final Object lock = new Object();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (lock) {
//                    System.out.println("T1-begin");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("T1-end");
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (lock) {
//                    System.out.println("T2-begin");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("T2-end");
//                }
//            }
//        }).start();

    }

    public static void queueTest() {
        final Object lock = new Object();

        for (int i = 0; i < 10; i++) {
            final int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("T" + w + "-entering");
                    synchronized (lock) {
                        System.out.println("T" + w + "-begin");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("T" + w + "-end");
                    }
                }
            }).start();
        }
    }
}
