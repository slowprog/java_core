package com.company;

public class BoxGen<T> {
    private T obj;

    public BoxGen(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void method() {
        // Нельзя делать следующее
        // T tmp = new T(); // Создавтьа объект

        // T[] array = new T[20]; // Создавть массив объектов
        // Работает Е только с сылочноми массивами данных
    }

    public void info() {
        System.out.println("Box: " + obj.toString());
    }
}
