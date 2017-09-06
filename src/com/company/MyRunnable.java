package com.company;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
