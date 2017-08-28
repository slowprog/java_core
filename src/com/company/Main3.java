package com.company;

public class Main3 {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }
}
