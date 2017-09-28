package com.company;

public class Human {
    int a;

    // Либо дефолтный констуктор должен быть либо родитель тоже должен быть Serialazible, в обраную сторону это будет работать норм, если Serialazible написано в родителе, то все наследники уже автоматом Serialazible
    public Human() {

    }

    public Human(int a) {
        this.a = a;
    }
}
