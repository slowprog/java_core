package com.new_project;

public class Main {
    public static void main(String[] args) {
        Team teamSuccess = new Team("Успех", new Animal[] {
            new Cat("Брск", 600, 1),
            new Cat("Пшок", 600, 3),
            new Dog("Дгги", 3000, 5, 100),
            new Dog("Псик", 300, 2, 300),
        });

        Course course = new Course(new Obstacle[] {
            new Cross(500),
            new Wall(2),
            new Water(5),
            new Cross(200),
        });

        course.start(teamSuccess);

        System.out.println("\nРезультаты прошедших все препятствия:");

        teamSuccess.showResultSuccessful();
    }
}
