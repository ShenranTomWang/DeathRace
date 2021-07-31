package test;

import model.Car;
import model.Position;
import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    public Car car;

    @BeforeEach
    public void setUp() {
        car = new Car(new Position(0, 0), 0);
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
        HashSet<Position> wall = car.move();
        System.out.println(wall);
        for (int i = 0; i < 10; i++) {
            assertTrue(wall.contains(new Position(0, i)));
        }
        assertEquals(new Position(0, Car.SPEED), car.getPos());
    }

    @Test
    public void testMoveS() {
        car.turnLeft();
        car.turnLeft();
        HashSet<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(0, -i)));
        }
        assertEquals(new Position(0, -Car.SPEED), car.getPos());
    }

    @Test
    public void testMoveW() {
        car.turnLeft();
        HashSet<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(-i, 0)));
        }
        assertEquals(new Position(-Car.SPEED, 0), car.getPos());
    }

    @Test
    public void testMoveE() {
        car.turnRight();
        HashSet<Position> wall = car.move();
        for (int i = 0; i < Car.SPEED; i++) {
            assertTrue(wall.contains(new Position(i, 0)));
        }
        assertEquals(new Position(Car.SPEED, 0), car.getPos());
    }
}
