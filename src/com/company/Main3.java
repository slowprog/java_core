package com.company;

public class Main3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1 start");
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 end");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2 start");
                try{
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 end");
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join(); // Ждать завершения потоков чтобы продолжить позже основной поток из main
            t2.join(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main end");
    }
}
