package com.repeat;

public abstract class Animal implements Swimmable {
    String name;
    String eyeColor;

    public Animal() {
        System.out.println("Констурктор родителя");
    }

    public Animal(String name) {
        this.name = name;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    // @Override // тут свой метод без параметров в отличии от Swimmable
    public void swim() {
        System.out.println("Плывём");
    }

    public abstract void voice();
}
