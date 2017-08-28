package com.new_project;

public class Cross extends Obstacle {
    private int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Challengeable animal) {
        animal.run(this.length);
    }
}
