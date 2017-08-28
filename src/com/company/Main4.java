package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main4 {
    public static void main(String[] args) {
        int x, y;
        try {
            x = 10 / 0; // unchecked exception
        } catch(ArithmeticException e) {
            x = 1;
        }

        y = x * 2;

        try {
            FileInputStream in = new FileInputStream("3.txt"); // checked exception
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
