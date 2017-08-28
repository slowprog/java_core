package com.company;

public class Main6 {
    public static void main(String[] args) {
        // try {
        //     int x = 10 / 0;
        // } catch(NullPointerException e) {
        //     System.out.println("Catch");
        //     e.printStackTrace();
        // } finally {
        //     System.out.println("Finally");
        // }
        //
        // System.out.println("End");

        System.out.println(a()); // 2
    }

    public static int a() {
        try {
            return 1;
        } finally { // Сильный блок, выполняется в любом случае
            return 2;
        }
    }
}
