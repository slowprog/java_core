package com.company;

import java.util.HashMap;
import java.util.TreeMap;

public class Main3 {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>(); // первое значение это ключ, поиск только по ключю, по значению ключ найти нельзя, но поиск по ключу ооочень быстрый

        hashMap.put("Rus", "Mow");
        hashMap.put("Ger", "Ber");
        hashMap.put("Fra", "Par");

        hashMap.put("Rus", "olol"); // перезапись возвращает предыдущее значение

        System.out.println(hashMap);

        TreeMap<String, String> treeMap = new TreeMap<>();

        treeMap.put("Rus", "Mow");
        treeMap.put("Ger", "Ber");
        treeMap.put("Fra", "Par");

        treeMap.put("Rus", "olol");

        System.out.println(treeMap);
    }
}
