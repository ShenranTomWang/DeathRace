package main.model;

import main.persistence.Writable;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

//This class represents a player
public class Player implements Writable {

    public static final String JSON_NAME = "name";
    public static final String JSON_SCORE = "score";
    public static final String JSON_COLOR = "color";

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

    public Player(String name, int score, Color color) {
        this.name = name;
        this.score = score;
        Random randX = new Random(Game.BOARD_X);
        Random randY = new Random(Game.BOARD_Y);
        this.car = new Car(new Position(randX.nextInt(), randY.nextInt()), 0, color);
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

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_NAME, name);
        jsonObject.put(JSON_SCORE, score);
        jsonObject.put(JSON_COLOR, car.getColor().getRGB());
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, name);
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
