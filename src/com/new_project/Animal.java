package com.new_project;

public class Animal implements Challengeable {
    protected int maxRunDistance;
    protected int maxJumpHeight;
    protected int maxSwimDistance;
    protected boolean inBusiness;

    protected String type;
    protected String name;

    public Animal(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.inBusiness = true;
    }

    @Override
    public void run(int distance) {
        if (distance <= this.maxRunDistance) {
            System.out.println(this.type + " " + this.name + " пробежал дистанцию " + distance);
        } else {
            this.inBusiness = false;
            System.out.println(this.type + " " + this.name + " не пробежал дистанцию " + distance + " т.к. может бегать только на " + this.maxRunDistance);
        }
    }

    @Override
    public void jump(int height) {
        if (height <= this.maxJumpHeight) {
            System.out.println(this.type + " " + this.name + " перепрыгнул препятствие " + height);
        } else {
            this.inBusiness = false;
            System.out.println(this.type + " " + this.name + " не перепрыгнул препятствие " + height + " т.к. может прыгать только на " + this.maxJumpHeight);
        }
    }

    @Override
    public void swim(int distance) {
        if (this.maxSwimDistance == 0) {
            System.out.println(this.type + " " + this.name + " не умеет плавать");
            this.inBusiness = false;
        } else {
            if (distance <= this.maxSwimDistance) {
                System.out.println(this.type + " " + this.name + " переплыл препятствие " + distance);
            } else {
                this.inBusiness = false;
                System.out.println(this.type + " " + this.name + " не переплыл препятствие " + distance + " т.к. может плавать только на " + this.maxSwimDistance);
            }
        }
    }

    @Override
    public void printResult() {
        if (this.inBusiness) {
            System.out.println(this.type + " " + this.name + " прошёл дистанцию");
        } else {
            System.out.println(this.type + " " + this.name + " не прошёл дистанцию");
        }
    }

    @Override
    public boolean isInBusiness() {
        return this.inBusiness;
    }
}
