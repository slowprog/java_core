package com.new_project;

public class Team {
    private String name;
    private Challengeable[] animals;

    public Team(String name, Challengeable[] animals) {
        this.name = name;
        this.animals = animals;
    }

    public void showResultSuccessful() {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].isInBusiness()) {
                animals[i].printResult();
            }
        }
    }

    public void showResult() {
        for (int i = 0; i < animals.length; i++) {
            animals[i].printResult();
        }
    }

    public void overcome(Obstacle obstacle) {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].isInBusiness()) {
                obstacle.doIt(animals[i]);
            }
        }
    }
}
