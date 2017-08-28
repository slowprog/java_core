package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 6};
        int[] array2 = new int[10];
        System.arraycopy(array, 0, array2, 0, 5);
        System.out.println(Arrays.toString(array2));

        ArrayList<String> arrayList = new ArrayList<>(); // нельзя с примитивными типами создать, только с обёртками (String, Integer, Boolean и др.)
        arrayList.add("str");
        arrayList.add("java");
        arrayList.add("Charrr");
        arrayList.add(1, "hell");
        // arrayList.add(1);
        System.out.println(arrayList);
        System.out.println(arrayList.get(1));

        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("C");
        arrayList1.add("A");
        arrayList1.add("B");
        arrayList1.add("B");
        arrayList1.add("C");
        arrayList1.add("C");
        arrayList1.remove(1);
        arrayList1.remove("C");
        System.out.println(arrayList1);
        while (arrayList1.remove("C"));
        arrayList1.set(0, "Charrr");
        arrayList1.removeAll(arrayList); // Пересечение удаляется из первого листа
        System.out.println(arrayList1);
        arrayList1.clear();
        System.out.println(arrayList1);

        ArrayList<String> arrayList2 = new ArrayList<>(10000000);
        arrayList2.add("C");
        arrayList2.add("C");
        arrayList2.trimToSize(); // уменьшает cpacity до текущего размера
        arrayList2.ensureCapacity(1); // увеличить текущую capacity до требуемого размера
        System.out.println(arrayList2);

        ArrayList<Box> boxArrayList = new ArrayList<>();
        boxArrayList.add(new Box(5));
        boxArrayList.add(new Box(1));
        boxArrayList.add(new Box(2));
        boxArrayList.add(new Box(3));
        boxArrayList.add(new Box(4));
        boxArrayList.add(new Box(6));

        boxArrayList.remove(new Box(6));
        System.out.println(boxArrayList);

        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list2.add(1);
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list2.remove(0);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - t));

        List<Integer> list1 = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(1);
        }
        t = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list1.remove(0);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - t));

        // LinkedList<String> linkedList = new LinkedList<>();
    }
}
