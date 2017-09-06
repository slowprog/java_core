package com.company;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int half = size / 2;

    public static void main(String[] args) {
        long resultFirst = firstMethod();

        System.out.println("Результат последовательного расчёта: " + resultFirst);

        long resultSecond = secondMethod();

        System.out.println("Результат параллельного расчёта: " + resultSecond);
    }

    private static long firstMethod() {
        float[] arr = new float[size];

        Arrays.fill(arr, 1);

        long time = System.currentTimeMillis();

        calcArray(arr);

        return System.currentTimeMillis() - time;
    }

    private static long secondMethod() {
        float[] arr = new float[size];
        float[] arrFirstHalf = new float[half];
        float[] arrSecondHalf = new float[half];

        Arrays.fill(arr, 1);

        long time = System.currentTimeMillis();

        System.arraycopy(arr, 0, arrFirstHalf, 0, half);
        System.arraycopy(arr, half, arrSecondHalf, 0, half);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                calcArray(arrFirstHalf);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                calcArray(arrSecondHalf);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arrFirstHalf, 0, arr, 0, half);
        System.arraycopy(arrSecondHalf, 0, arr, half, half);

        return System.currentTimeMillis() - time;
    }

    private static void calcArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
