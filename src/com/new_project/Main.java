package com.new_project;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Cat("Кот", 1000, 200, 0),
                new Dog("Дог", 3000, 200, 100),
        };

        Obstacle[] courses = {
                new Cross(500),
                new Wall(2),
                new Water(5),
                new Cross(200),
        };

        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < courses.length; j++) {
                courses[j].doIt(animals[i]);

                if (!animals[i].onDistance) {
                    break;
                }
            }
        }

        System.out.println("Результаты: ");

        animals[0].printResult();
        animals[1].printResult();
    }
}
