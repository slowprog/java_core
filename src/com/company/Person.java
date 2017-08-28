package com.company;

public class Person {
    public int age;
    public String name;
    public String id;

    public Person(int age) {
        if (age < 0) {
            throw new RuntimeException("Указан отрицательный возраст");
        }

        this.age = age;
    }

    public Person(String name, String id, int age) {
        if (age < 0) {
            throw new PersonException(name, id);
        }

        this.age = age;
    }
}
