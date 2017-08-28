package com.new_project;

public class Water extends Obstacle {
    private int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Challengeable animal) {
        animal.swim(this.length);
    }
}
