package com.repeat;

public class Main {
    public static void main(String[] args) {
        Cat[] cats = new Cat[2];
        cats[0] = new Cat("Barsik");
        Cat[] cats1 = {
                new Cat("Barsik2"),
                new Cat("Barsik1")
        };

        System.out.println("Переменная: " + Cat.catsCount);
        System.out.println("Метод: " + Cat.getCatsCount());

        Animal animal = new Cat();

        if (animal instanceof Cat) {
            ((Cat)animal).age = 4;
        }

        (new Lion()).swim();
    }
}
