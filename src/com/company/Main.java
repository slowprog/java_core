package com.company;

public class Main {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
        } catch(ArithmeticException e) {
            e.printStackTrace();
        }

        try {
            String str = null;
            System.out.println(str.charAt(1));
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        try {
            int[] arr = new int[5];
            System.out.println(arr[10]);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }
}
