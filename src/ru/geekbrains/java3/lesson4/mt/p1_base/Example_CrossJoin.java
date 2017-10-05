package ru.geekbrains.java3.lesson4.mt.p1_base;

public class Example_CrossJoin {
    static Thread1 t1;
    static Thread2 t2;

    static class Thread1 extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("A1S");
                Thread.sleep(2000);
                t2.join();
                System.out.println("A1E");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("A2S");
                Thread.sleep(2000);
                t1.join();
                System.out.println("A2E");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        t1 = new Thread1();
        t2 = new Thread2();
        t1.start();
        t2.start();
    }
}
