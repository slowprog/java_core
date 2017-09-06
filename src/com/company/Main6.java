package com.company;

public class Main6 {
    public static void main(String[] args) {
        ATM atm = new ATM(100);

        User user1 = new User("Вася");
        User user2 = new User("Петя");
        User user3 = new User("Коля");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.withdraw(50, user1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.withdraw(50, user2);
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.withdraw(50, user3);
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        atm.info();
    }
}
