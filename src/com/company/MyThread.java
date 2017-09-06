package com.company;

public class MyThread extends Thread {
    @Override
    public void run(){
        for (int i = 0; i < 9; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // while (true);
        }

        // Thread t1 = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         for (int i = 0; i < 100; i++) {
        //             System.out.println(i);
        //         }
        //     }
        // });
        // t1.start();
    }
}
