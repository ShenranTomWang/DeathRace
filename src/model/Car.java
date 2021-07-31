package model;

import java.util.HashSet;

public class Car {
    public static final int SPEED = 10;
    public static final int TURN_ANGLE = 90;

    private Position pos;
    private int direction;                            //Can only be within range 0 - 360, 0 is North

    //REQUIRES: pos is within board range
    //EFFECTS: instantiate new Car at pos
    public Car(Position pos, int direction) {
        this.pos = pos;
        this.direction = direction;
    }

    //MODIFIES: this
    //EFFECTS: set direction to direction - TURN_ANGLE
    public void turnLeft() {
        int dir = direction;
        direction -= TURN_ANGLE;
        if (direction < 0) {
            direction = 360 - (TURN_ANGLE - dir);
        }
    }

    //MODIFIES: this
    //EFFECTS: set direction to direction + TURN_ANGLE
    public void turnRight() {
        int dir = direction;
        direction += TURN_ANGLE;
        if (direction > 360) {
            direction = (TURN_ANGLE + dir) - 360;
        }
    }

    //MODIFIES: this
    //EFFECTS: move car SPEED towards direction, and record positions along the way in wall
    public HashSet<Position> move() {
        HashSet<Position> wall = new HashSet<>();
        for (int i = 0; i < SPEED; i++) {
            if (direction == 0 || direction == 360) {
                wall.add(new Position(pos.getX(), pos.getY()));
                pos.moveY(1);
            } else if (direction == 90) {
                wall.add(new Position(pos.getX(), pos.getY()));
                pos.moveX(1);
            } else if (direction == 180) {
                wall.add(new Position(pos.getX(), pos.getY()));
                pos.moveY(-1);
            } else if (direction == 270) {
                wall.add(new Position(pos.getX(), pos.getY()));
                pos.moveX(-1);
            }
        }
        return wall;
    }

    //getters

    public Position getPos() {
        return pos;
    }

    public int getDirection() {
        return direction;
    }
}
