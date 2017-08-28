package com.new_project;

public class Animal implements Participant {
    protected int maxRunDistance;
    protected int maxJumpHeight;
    protected int maxSwimDistance;
    protected boolean onDistance;

    protected String type;
    protected String name;

    public Animal(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    @Override
    public void run(int distance) {
        if (distance <= this.maxRunDistance) {
            System.out.println(this.type + " " + this.name + " пробежал дистанцию");
        } else {
            this.onDistance = false;
            System.out.println(this.type + " " + this.name + " не пробежал дистанцию");
        }
    }

    @Override
    public void jump(int height) {
        if (height <= this.maxJumpHeight) {
            System.out.println(this.type + " " + this.name + " перепрыгнул препятствие");
        } else {
            this.onDistance = false;
            System.out.println(this.type + " " + this.name + " не перепрыгнул препятствие");
        }
    }

    @Override
    public void swim(int distance) {
        if (this.maxSwimDistance == 0) {
            System.out.println(this.type + " " + this.name + " не умеет плавать");
            this.onDistance = false;
        } else {
            if (distance <= this.maxSwimDistance) {
                System.out.println(this.type + " " + this.name + " переплыл препятствие");
            } else {
                this.onDistance = false;
                System.out.println(this.type + " " + this.name + " не переплыл препятствие");
            }
        }
    }

    @Override
    public void printResult() {
        if (this.onDistance) {
            System.out.println(this.type + " " + this.name + " прошёл дистанцию");
        } else {
            System.out.println(this.type + " " + this.name + " не прошёл дистанцию");
        }
    }
}
