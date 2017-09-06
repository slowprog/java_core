package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread1.start(); // Запускает поток
        // myThread2.start(); // Простые операции не распараллеливают поток, а сам метода start вызвать можно один раз

        // myThread1.run(); // Это обычный вызов метода, который не распараллеливается
        // myThread2.run();
        //
        // Scanner scan = new Scanner(System.in);
        // String str = scan.nextLine();
        // System.out.println(str);

        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();

    }
}
