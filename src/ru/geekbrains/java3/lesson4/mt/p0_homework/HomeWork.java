package ru.geekbrains.java3.lesson4.mt.p0_homework;

// Домашнее задание:
// 1) Прочитать файл(около 50 байт) в байтовый массив и вывести этот массив в консоль;
// 2) Последовательно сшить 10 файлов в один(файлы также ~100 байт).
// Может пригодиться следующая конструкция:
// ArrayList<FileInputStream> al = new ArrayList<>();
// ...
// Enumeration<FileInputStream> e = Collections.enumeration(al);
// 3) Написать консольное приложение, которое умеет постранично читать текстовые файлы(размером > 10 mb),
// вводим страницу, программа выводит ее в консоль(за страницу можно принимаем 1800 символов).
// Время чтения файла должно находится в разумных пределах(программа не должна загружаться дольше 10 секунд),
// ну и чтение тоже не должно занимать >5 секунд.
// Чтобы не было проблем с кодировкой используйте латинские буквы.

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args)  {
        File file = new File("123");
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.length());

//        BufferedReader br = new BufferedReader(new FileReader("2.txt"));
//        char[] arr = new char[10];
//        int n;
//        while ((n = br.read(arr)) > 0) {
//            System.out.println(n);
//        }
//        br.close();

//        try {
////            task1();
//            // task2();
//            task3();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void task3() throws IOException {
        final int PAGE_SIZE = 1800;
        RandomAccessFile raf = new RandomAccessFile("1.txt", "r");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter page:");
        int p = sc.nextInt() - 1;
        raf.seek(p * PAGE_SIZE);
        byte[] barr = new byte[1800];
        raf.read(barr);
        System.out.println(new String(barr));
        raf.close();
    }

    public static void task2() throws IOException {
        MyTimer.start();
        ArrayList<InputStream> al = new ArrayList<>();
        al.add(new FileInputStream("1.txt"));
        al.add(new FileInputStream("2.txt"));
        al.add(new FileInputStream("3.txt"));
        BufferedInputStream in = new BufferedInputStream(new SequenceInputStream(Collections.enumeration(al)));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("out.txt"));
        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
            System.out.print((char) x);
        }
        in.close();
        out.close();
        MyTimer.stopAndPrint();
    }

    public static void task1() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("2.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
        }
        byte[] b = out.toByteArray();
        System.out.println(Arrays.toString(b));
        in.close();
        out.close();
    }
}
