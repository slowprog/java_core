package ru.geekbrains.java3.lesson4.mt.p1_base;

import ru.geekbrains.java3.lesson4.mt.p0_homework.MyTimer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example_NewThreadsVSExecutorService {
    public static void main(String[] args) {
        final int CLIENTS_COUNT = 100000;
        CountDownLatch cdl = new CountDownLatch(CLIENTS_COUNT);
        MyTimer.start();
        for (int i = 0; i < CLIENTS_COUNT; i++) {
            final int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cdl.countDown();
                }
            }).start();
        }
        try {
            cdl.await(); // Ждем пока все потоки сделают countDown()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyTimer.stopAndPrint();
    }

//    public static void main(String[] args) {
//        final int CLIENTS_COUNT = 100000;
//        CountDownLatch cdl = new CountDownLatch(CLIENTS_COUNT);
//        ExecutorService serv = Executors.newFixedThreadPool(2000); // Пул, который создаем потоки по мере необходимости
//        MyTimer.start();
//        for (int i = 0; i < CLIENTS_COUNT; i++) {
//            final int w = i;
//            serv.execute(new Runnable() {
//                @Override
//                public void run() {
////                    try {
////                        Thread.sleep(10);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                    cdl.countDown();
//                }
//            });
//        }
//        serv.shutdown(); // ExecutorService "Перестает принимать заказы"
//        try {
//            cdl.await(); // Ждем пока все потоки сделают countDown()
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        MyTimer.stopAndPrint();
//    }


}
