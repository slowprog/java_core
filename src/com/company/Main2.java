package com.company;

public class Main2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        time++;
                        System.out.println("Time: " + time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.setPriority(10); // 1 - 10, как часто нужно выделять процессорные такты под этот поток
        t1.setDaemon(true); // Поток не важен и он не влияет на работу программы, его дожидаться не будут
        t1.start();

        try{
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt(); // Прерывает выполнение потока, завершение мягкое, позволяющее потоку завершиться корректно (какие-то действия произвести, закрыть, например, соединение с базой)
        // t1.stop(); // Принудительное завершение в момент
        // while (!t1.isInterrupted()) {
        //
        // }
        System.out.println("End");
    }
}
