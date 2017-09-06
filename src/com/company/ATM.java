package com.company;

public class ATM {
    private int money;

    public ATM(int money) {
        this.money = money;
    }

    public synchronized void withdraw(int amount, User u) {
        if (this.money >= amount) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.money -= amount;
            System.out.println("Снято " + amount + " человеком " + u.getName());
        } else {
            System.out.println("Человеку " + u.getName() + " не хватило денег");
        }
    }

    public void info() {
        System.out.println("ATM: " + this.money);
    }
}
