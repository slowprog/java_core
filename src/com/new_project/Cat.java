package com.new_project;

public class Cat extends Animal {
    public Cat(String name, int maxRunDistance, int maxJumpHeight) {
        super(name, maxRunDistance, maxJumpHeight, 0);

        this.type = "Cat";
    }
}
