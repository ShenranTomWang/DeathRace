package model;

import java.awt.*;
import java.util.ArrayList;

public class Car implements Item {

    public static final int SIZE_X = 10;
    public static final int SIZE_Y = 10;
    public static final int SPEED = 1;
    public static final int TURN_ANGLE = 90;

    private Color color;
    private int speed;
    private Position pos;
    private int direction;                            //Can only be within range 0 - 360, 0 is North
    private boolean collided;

    //REQUIRES: pos is within board range
    //EFFECTS: instantiate new Car at pos
    public Car(Position pos, int direction, Color color) {
        this.pos = pos;
        this.direction = direction;
        this.speed = SPEED;
        this.collided = false;
        this.color = color;
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
    public ArrayList<Position> move() {
        ArrayList<Position> wall = new ArrayList<>();
        if (direction == 0 || direction == 360) {
            wall.add(new Position(pos.getX(), pos.getY()));
            pos.moveY(-speed);
        } else if (direction == 90) {
            wall.add(new Position(pos.getX(), pos.getY()));
            pos.moveX(speed);
        } else if (direction == 180) {
            wall.add(new Position(pos.getX(), pos.getY()));
            pos.moveY(speed);
        } else if (direction == 270) {
            wall.add(new Position(pos.getX(), pos.getY()));
            pos.moveX(-speed);
        }
        return wall;
    }

    //MODIFIES: this
    //EFFECTS: set collided to true, stop the car
    public void setCollided() {
        this.collided = true;
        this.speed = 0;
    }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(color);
        g.fillOval(pos.getX() - SIZE_X / 2, pos.getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedCol);
    }

    //getters

    public Position getPos() {
        return pos;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isCollided() {
        return collided;
    }

    public int getSpeed() {
        return speed;
    }

    public Color getColor() {
        return color;
    }
}
