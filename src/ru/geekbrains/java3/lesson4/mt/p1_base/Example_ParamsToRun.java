package ru.geekbrains.java3.lesson4.mt.p1_base;

public class Example_ParamsToRun implements Runnable {
    private int z;

    public Example_ParamsToRun(int z) {
        this.z = z;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(z);
        }
    }
}
