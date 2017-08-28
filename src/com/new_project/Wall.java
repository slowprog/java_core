package com.new_project;

public class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Challengeable animal) {
        animal.jump(this.height);
    }
}
