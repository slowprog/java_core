package ru.geekbrains.java3.lesson4.mt.p1_base;

import ru.geekbrains.java3.lesson4.mt.p0_homework.MyTimer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example_Base_3 {

    // Создание потоков с помощью ExecutorService

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(10);
        ExecutorService serv = Executors.newFixedThreadPool(20); // Пул, который создаем потоки по мере необходимости
        MyTimer.start();
        for (int i = 0; i < 10; i++) {
            final int w = i;
            serv.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(w + " - Begin");
                    try {
                        Thread.sleep(100 + (int) (3000 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(w + " - End");
                    cdl.countDown();
                }
            });
        }
        serv.shutdown(); // ExecutorService "Перестает принимать заказы"
        try {
            cdl.await(); // Ждем пока все потоки сделают countDown()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyTimer.stopAndPrint();
    }



}
