package ru.geekbrains.java3.lesson4.mt.p1_base;

public class Example_Base_2 {

    // Тестирование Daemon-потока, который работает пока есть хоть один рабочий обычный поток
    // В данном случае он просто отсчитвает секунды

    public static void main(String[] args) {
        Thread tTimer = new Thread(() -> {
            int time = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    time++;
                    System.out.println("time: " + time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        tTimer.setDaemon(true);
        tTimer.start();
        System.out.println("main -> sleep");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main -> end");
    }
}
