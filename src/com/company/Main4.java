package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class Main4 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < arrayList.size(); i++) {
           if (arrayList.get(i) > 10 && arrayList.get(i) < 20) {
               arrayList.remove(i); // из-за смещения индексов каждый второй не будет удаляться
           }
        }

        System.out.println(arrayList);

        Iterator<Integer> iter = arrayList.iterator();

        while (iter.hasNext()) {
            int a = iter.next();
            if (a > 20 && a < 30) {
                iter.remove();
            }
        }

        System.out.println(arrayList);

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(2);
        arrayList1.add(4);
        arrayList1.add(2);

        Iterator<Integer> iter1 = arrayList1.iterator();

        while (iter1.hasNext()) {
            // int a = iter1.next(); // Два раза next не должно быть т.к. тогда выйдем за пределы массива
            if (iter1.next() == 2) {
                iter1.remove();
            }
        }

        System.out.println(arrayList1);
    }
}
