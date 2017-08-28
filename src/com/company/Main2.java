package com.company;

import java.lang.reflect.Array;

public class Main2 {
    public static void main(String[] args) {
        try {
            try {
                int x = 10 / 0;
            } catch(ArithmeticException e) {
                System.out.println("ArithmeticException - 1");
            }

            int[] array = new int[5];
            System.out.println(array[10]);

        } catch(ArithmeticException e) {
            System.out.println("ArithmeticException");
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }

        try {
            String str = null;
            str.charAt(4);
        } catch(NullPointerException e) {
            System.out.println("NullPointerException");
        } catch(Exception e) { // Юзабильный вариант, когдав конце общий перехватывается
            System.out.println("Exception");
        }
    }
}
