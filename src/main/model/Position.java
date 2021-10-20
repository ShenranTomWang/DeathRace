package main.model;

import java.awt.*;
import java.util.Objects;

public class Position implements Drawable {

    public static final Color COLOR = Color.orange;
    public static final int SIZE_X = 5;
    public static final int SIZE_Y = 5;

    private int x;
    private int y;

    //REQUIRES: x and y are in board range
    //EFFECTS: instantiate new Position at (x, y)
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //REQUIRES: amount is within board range
    //MODIFIES: this
    //EFFECTS: x += amount
    public void moveX(int amount) {
        x += amount;
    }

    //REQUIRES: amount is within board range
    //MODIFIES: this
    //EFFECTS: y += amount
    public void moveY(int amount) {
        y += amount;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(x - SIZE_X / 2, y - SIZE_Y / 2, SIZE_X, SIZE_Y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    //getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
