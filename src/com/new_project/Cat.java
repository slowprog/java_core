package com.new_project;

public class Cat extends Animal {
    public Cat(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super(name, maxRunDistance, maxJumpHeight, maxSwimDistance);

        this.type = "Cat";
    }
}
