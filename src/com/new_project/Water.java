package com.new_project;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Animal animal) {
        animal.swim(this.length);
    }
}
