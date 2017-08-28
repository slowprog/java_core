package com.company;

public class Main5 {
    public static void main(String[] args) {
        System.out.println((new Person(5)).age);

        try {
            System.out.println((new Person(-5)).age);
        } catch(RuntimeException e) {
            System.out.println("Забей");
        }

        result(1, 2, 3, 0);
        System.out.println("Конец");
    }

    // public static int result(int a, int b, int c, int d) {
    //     if (d == 0) {
    //         throw new RuntimeException("Деление на ноль")
    //     }
    //
    //     return a + b * c / d;
    // }

    public static int result(int a, int b, int c, int d) throws RuntimeException {
        return a + b * c / d;
    }
}
