package ru.geekbrains.java3.lesson4.mt.p1_base;

// Класс, реализующий интерфейс Runnable

public class RunnableExampleClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
