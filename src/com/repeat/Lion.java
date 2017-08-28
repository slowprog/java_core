package com.repeat;

public class Lion extends Animal implements Predator, Swimmable {
    int x = 5;

    @Override
    public void swim() {
        super.swim();

        System.out.println("sx = " + super.x);
        System.out.println("x = " + x);
        System.out.println("Sx = " + Swimmable.x);
    }
    @Override
    public void voice() {
        System.out.println("Rrrr");
    }
}
