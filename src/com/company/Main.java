package com.company;

import java.io.*;
import java.util.*;

/**
 * !!! Не смог реализовать постраничное чтение. Пробовал по-разному, но ничего не вышло: новые символы не появлялись в
 * потоке или в ридере. Даже по одному символу выводить не получилось. Последнее что попробовал это RandomAccessFile с
 * постоянным открытием и закрытием файла, думал, что новые символы подхватятся, но на деле оказалось не так.
 */
public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    private static void task2() {
        System.out.println("Задание 2:");

        String pathOutFile = "dir/100b.txt";

        File file = new File(pathOutFile);
        file.delete();

        FileOutputStream out = null;
        SequenceInputStream in = null;

        try {
            out = new FileOutputStream(pathOutFile);

            ArrayList<FileInputStream> ali = new ArrayList<>(Arrays.asList(
                    new FileInputStream("dir/20b_1.txt"),
                    new FileInputStream("dir/20b_2.txt"),
                    new FileInputStream("dir/20b_3.txt"),
                    new FileInputStream("dir/20b_4.txt"),
                    new FileInputStream("dir/20b_5.txt")
            ));

            in = new SequenceInputStream(Collections.enumeration(ali));
            int x = -1;
            while ((x = in.read()) != -1) {
                out.write(x);
                System.out.print((char)x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void task1() {
        System.out.println("Задание 1:");
        try (FileInputStream in = new FileInputStream("dir/50b.txt")) {
            byte[] arr = new byte[50];
            while (in.read(arr) > 0) {
                System.out.println(Arrays.toString(arr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task3() {
        int lastLength = 0;
        while (true) {
            try (RandomAccessFile raf = new RandomAccessFile("dir/10M.txt", "r")) {
                raf.seek(lastLength);
                int symb;
                while ((symb = raf.read()) != -1) {
                    System.out.print((char)symb);
                    lastLength++;
                }
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // try (FileReader in = new FileReader("dir/10M.txt")) {
        //     int symb;
        //     while (true) {
        //         symb = in.read();
        //         if (symb == -1) {
        //             Thread.sleep(500);
        //         } else {
        //             System.out.print((char)symb);
        //         }
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
