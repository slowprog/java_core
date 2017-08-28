package com.new_project;

public class Wall extends Obstacle {
    int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Animal animal) {
        animal.jump(this.height);
    }
}
