package com.company;

import java.io.Serializable;

public class Student extends Human implements Serializable { // Externalizable позволяет самостоятельно управлять процессом сериализации, нужно реализовать какие-то там методы
    String name;
    int score;
    Book book;
    transient String pass; // это поле не учитывается при сериализации и не сохраняется

    public Student(String name, int score) {
        super(0);

        this.name = name;
        this.score = score;
        System.out.println("Constructor");
    }

    @Override
    public String toString() {
        return "Student " + this.name + " " + this.score;
    }
}
