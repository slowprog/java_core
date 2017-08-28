package com.repeat;

public interface Swimmable { // Желательное окончания "able" (что-то делающий)
    int x = 10; // Это константа

    // public void swim();
    default void swim(int distance) {  // в интерфейсах есть дефолтная реализация
        System.out.println(distance);
    }

    static void swim1(int distance) {
        System.out.println(distance);
    }
}
