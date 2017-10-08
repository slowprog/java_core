package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Main {
    public static final int CARS_COUNT = 5;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Semaphore tunnelCapacity = new Semaphore(CARS_COUNT / 2);
        CyclicBarrier racePoints = new CyclicBarrier(CARS_COUNT + 1);
        ArrayBlockingQueue<String> photoFinishQueue = new ArrayBlockingQueue<String>(CARS_COUNT);

        Thread photoFinish = new Thread(new Runnable() {
            @Override
            public void run() {
                int place = 0;
                String name = null;

                try {
                    while (!((name = photoFinishQueue.take()).equals("finish"))) {
                        if (++place == 1) {
                            System.out.println(name + " это WIN!");
                        } else {
                            System.out.println(name + " занял место " + place);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Race race = new Race(new Road(60), new Tunnel(tunnelCapacity), new Road(40));

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), racePoints, photoFinishQueue);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            racePoints.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            photoFinish.start();

            racePoints.await();

            racePoints.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

            photoFinishQueue.put("finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    CyclicBarrier racePoints;
    ArrayBlockingQueue<String> photoFinishQueue;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier racePoints, ArrayBlockingQueue<String> photoFinishQueue) {
        this.race = race;
        this.speed = speed;
        this.racePoints = racePoints;
        this.photoFinishQueue = photoFinishQueue;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            // Все ожидают начала гонки
            this.racePoints.await();

            // Все одинаково начали гонку
            this.racePoints.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            try {
                this.photoFinishQueue.put(this.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.racePoints.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(this.length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Tunnel extends Stage {
    Semaphore tunnelCapacity;

    public Tunnel(Semaphore tunnelCapacity) {
        this.length = 80;
        this.tunnelCapacity = tunnelCapacity;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу (ждет): " + description);

                this.tunnelCapacity.acquire();

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                this.tunnelCapacity.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}