package ru.geekbrains.java3.lesson4.mt.p1_base;

import ru.geekbrains.java3.lesson4.mt.p0_homework.MyTimer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example_ShutdownNow {
    public static void main(String[] args) {
        ExecutorService serv = Executors.newFixedThreadPool(5); // Пул, который создаем потоки по мере необходимости
        for (int i = 0; i < 2; i++) {
            final int w = i;
            serv.execute(new Runnable() {
                @Override
                public void run() {
                    float x = 0.0f;
                    for (int j = 0; j < 10; j++) {
                        if (Thread.currentThread().isInterrupted()) break;
                        System.out.println(j);
                        for (int k = 0; k < 10000000; k++) {

                            x = (float) Math.cos(1000.0f) + (float) Math.cos(1000.0f);
                        }

                    }
                }
            });
        }

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serv.shutdownNow();
    }
}
