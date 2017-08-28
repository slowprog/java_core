package com.repeat;

public class Cat extends Animal {
    public static int catsCount;
    private String color;
    protected int age;

    public Cat() {
        catsCount++;
        // supper(); // Строка существуется всегда при наличии пустого конструктора
    }

    public Cat(String name) {
        this.name = name;
        catsCount++;
    }

    public Cat(String name, String eyeColor) {
        this.name = name;
        this.setEyeColor(eyeColor);
        catsCount++;
    }

    public String getColor() {
        return color;
    }

    public void getInfo() {
        System.out.println(this.name + " " + this.color);
    }

    public static int getCatsCount() {
        return catsCount;
    }

    @Override
    public void voice() {
        System.out.println("Мау");
    }
}
