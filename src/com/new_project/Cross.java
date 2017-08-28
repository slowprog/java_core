package com.new_project;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Animal animal) {
        animal.run(this.length);
    }
}
