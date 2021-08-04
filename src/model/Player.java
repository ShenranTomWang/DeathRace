package model;

import java.awt.*;

//This class represents a player
public class Player {

    private Car car;
    private int score;
    private String name;

    //REQUIRES: pos is within board range, direction is in [0, 360]
    //EFFECTS: instantiates new Player with name, car at pos, and score = 0
    public Player(String name, Position pos, int direction, Color color) {
        this.name = name;
        this.score = 0;
        this.car = new Car(pos, direction, color);
    }

    //REQUIRES: x and y are within board range, direction is in [0, 360]
    //EFFECTS: instantiates new Player with name, car at (x, y), and score = 0;
    public Player(String name, int x, int y, int direction, Color color) {
        this.name = name;
        this.score = 0;
        this.car = new Car(new Position(x, y), direction, color);
    }

    //MODIFIES: this
    //EFFECTS: score + 1
    public void addOneToScore() {
        score++;
    }

    //MODIFIES: this
    //EFFECTS: reset the car
    public void resetCar(Position pos, int direction) {
        this.car = new Car(pos, direction, car.getColor());
    }

    //getters

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Car getCar() {
        return car;
    }
}
