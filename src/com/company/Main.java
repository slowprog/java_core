package com.company;

import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] arr1 = new String[] {"1фыва", "2qwer"};
        Integer[] arr2 = new Integer[] {1, 2};

        System.out.println("Задания 1.");
        System.out.println(Arrays.toString(change(arr1)));
        System.out.println(Arrays.toString(change(arr2)));

        System.out.println("Задания 2.");
        ArrayList<String> arrayList1 = transform(arr1);
        ArrayList<Integer> arrayList2 = transform(arr2);
        System.out.println(arrayList1.getClass());
        System.out.println(arrayList2.getClass());

        System.out.println("Задания 3.");
        Box<Apple> boxApple1 = new Box<>();
        boxApple1.add(new Apple());
        boxApple1.add(new Apple());
        // boxApple1.add(new Orange());
        System.out.println("Вес первой коробки с яблоками: " + boxApple1.getWeight());

        Box<Apple> boxApple2 = new Box<>();
        boxApple2.add(new Apple());
        boxApple2.add(new Apple());
        boxApple2.add(new Apple());
        System.out.println("Вес второй коробки с яблоками: " + boxApple2.getWeight());

        Box<Orange> boxOrange = new Box<>();
        boxOrange.add(new Orange());
        boxOrange.add(new Orange());
        System.out.println("Вес коробки с апельсинами: " + boxOrange.getWeight());

        System.out.println("Сравнили коробки с яблоками: " + boxApple1.compare(boxApple2));
        System.out.println("Сравнили первую коробку с яблоками с коробкой с апельсинами: " + boxApple1.compare(boxOrange));
        System.out.println("Сравнили вторую коробку с яблоками с коробкой с апельсинами: " + boxApple2.compare(boxOrange));

        System.out.println("Пересыпаем яблоки из первой коробки во вторую");
        boxApple1.transferTo(boxApple2);
        // boxApple1.transferTo(boxOrange);
        System.out.println("Вес первой коробки с яблоками: " + boxApple1.getWeight());
        System.out.println("Вес второй коробки с яблоками: " + boxApple2.getWeight());
    }

    // Задания 1.
    public static <T> T[] change(T[] arr) {
        T tmp = arr[0];
        arr[0] = arr[1];
        arr[1] = tmp;

        return arr;
    }

    // Задания 2.
    public static <T> ArrayList<T> transform(T[] arr) {
        ArrayList<T> tmp = new ArrayList<T>(Arrays.asList(arr));

        return tmp;
    }
}

// Задание 3.
class Fruit {
    protected double weight;

    public double getWeight() {
        return weight;
    }
}
class Apple extends Fruit {
    public Apple() {
        weight = 1.0f;
    }
}
class Orange extends Fruit {
    public Orange() {
        weight = 1.5f;
    }
}
class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public double getWeight() {
        double sum = 0.0f;

        for (T f : fruits) {
            sum  += f.getWeight();
        }

        return sum;
    }

    public boolean compare(Box box) {
        return Math.abs(getWeight() - box.getWeight()) < 0.00001;
    }

    public void transferTo(Box<T> box) {
        for (T f : fruits) {
            box.add(f);
        }

        fruits.clear();
    }
}