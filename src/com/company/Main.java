package com.company;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        hashWords();
        phonebook();
    }

    public static void hashWords() {
        System.out.println("== Первое задание: ====");

        String[] words = {
                "слово", "слово", "слово", "слово", "слово", "слово", "слово", "славо", "слово", "слово", "слову", "слово",
                "слово", "слово", "слово", "славо", "слово", "слово", "слова", "слово", "слово", "слово", "совой", "слово",
        };

        System.out.println("Начальный размер: " + words.length);

        HashMap<String, Integer> hashMapWords = new HashMap<>();
        Integer count;

        for (int i = 0; i < words.length; i++) {
            count = hashMapWords.get(words[i]);
            if (count == null) {
                count = 0;
            }
            hashMapWords.put(words[i], ++count);
        }

        System.out.println("Конечный размер: " + hashMapWords.size());
        System.out.println(hashMapWords);
        System.out.println("=======================");
    }

    public static void phonebook() {
        System.out.println("== Второе задание: ====");

        Phonebook phonebook = new Phonebook();

        phonebook.add("Петров", "+7 (711) 123-12-12");
        phonebook.add("Петров", "+7 (231) 555-66-44");
        phonebook.add("Сидоров", "+7 (111) 333-12-12");

        System.out.println("Петров: " + phonebook.get("Петров"));
        System.out.println("Сидоров: " + phonebook.get("Сидоров"));
        System.out.println("=======================");
    }
}
