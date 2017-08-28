package com.new_project;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public void start(Team team) {
        for (int j = 0; j < obstacles.length; j++) {
            team.overcome(obstacles[j]);

            System.out.println("=====================");
        }
    }
}
