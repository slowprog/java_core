package ru.geekbrains.java3.lesson4.mt.p1_base;

public class Example_Base_1 {
    public static void main(String[] args) {
        // Создаем объект подкласса Thread и запускаем поток
        ThreadExampleClass t1 = new ThreadExampleClass();
        t1.start();
        // Создаем объект класса, реализующего интерфейс Runnable,
        // "заворачиваем" его в объект класса Thread и запускаем поток
        Thread t2 = new Thread(new RunnableExampleClass());
        t2.start();

        System.out.println("Begin");
        try {
            t1.join(); // Ожидаем пока первый поток выполнится
            // System.out.println("t1 end");
            t2.join(); // Ожидаем пока второй поток выполнится
            // System.out.println("t2 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
