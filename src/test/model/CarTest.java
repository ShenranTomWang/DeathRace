package test.model;

import main.model.Car;
import main.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//This class is the test class for Car
public class CarTest {

    public Car car;

    @BeforeEach
    public void setUp() {
        car = new Car(new Position(50, 50), 0, Color.BLUE);
    }

    @Test
    public void testTurnLeft() {
        car.turnLeft();
        assertEquals(270, car.getDirection());
        car.turnLeft();
        assertEquals(180, car.getDirection());
        car.turnLeft();
        assertEquals(90, car.getDirection());
        car.turnLeft();
        assertEquals(0, car.getDirection());
        car.turnLeft();
        assertEquals(270, car.getDirection());
    }

    @Test
    public void testTurnRight() {
        car.turnRight();
        assertEquals(90, car.getDirection());
        car.turnRight();
        assertEquals(180, car.getDirection());
        car.turnRight();
        assertEquals(270, car.getDirection());
        car.turnRight();
        assertEquals(360, car.getDirection());
        car.turnRight();
        assertEquals(90, car.getDirection());
    }

    @Test
    public void testMoveN() {
        int x = car.getPos().getX();
        int y = car.getPos().getY();
        ArrayList<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(x, y + i)));
        }
        assertEquals(new Position(x, y - Car.SPEED), car.getPos());
    }

    @Test
    public void testMoveS() {
        car.turnLeft();
        car.turnLeft();
        int x = car.getPos().getX();
        int y = car.getPos().getY();
        ArrayList<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(x, y - i)));
        }
        assertEquals(new Position(x, y + Car.SPEED), car.getPos());
    }

    @Test
    public void testMoveW() {
        car.turnLeft();
        int x = car.getPos().getX();
        int y = car.getPos().getY();
        ArrayList<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(x - i, y)));
        }
        assertEquals(new Position(x - Car.SPEED, y), car.getPos());
    }

    @Test
    public void testMoveE() {
        car.turnRight();
        int x = car.getPos().getX();
        int y = car.getPos().getY();
        ArrayList<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(x + i, y)));
        }
        assertEquals(new Position(x + Car.SPEED, y), car.getPos());
    }

    @Test
    public void testCollision() {
        car.setCollided();
        assertTrue(car.isCollided());
        assertEquals(0, car.getSpeed());
    }

    @Test
    public void testStop() {
        car.stop();
        assertEquals(0, car.getSpeed());
    }
}
