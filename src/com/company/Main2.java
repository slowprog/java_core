package com.company;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Main2 {
    public static void main(String[] args) {
        HashSet<Box> hashSet = new HashSet<>();
        hashSet.add(new Box(3));
        hashSet.add(new Box(4));
        hashSet.add(new Box(5));
        hashSet.add(new Box(5));
        System.out.println(hashSet);

        HashSet<String> hashSet1 = new HashSet<>(); // Расположение элементов зависит от hashCode элемента

        for (int i = 0; i < 20; i++) {
            hashSet1.add(String.valueOf(i));
            System.out.println(hashSet1);
        }

        System.out.println(hashSet1);

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        TreeSet<String> treeSet = new TreeSet<>();

        for (int i = 0; i < 20; i++) {
            treeSet.add(String.valueOf(i));
            System.out.println(treeSet);
        }

        System.out.println(treeSet);

    }
}
