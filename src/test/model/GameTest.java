package test.model;

import main.model.Car;
import main.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameTest {

    public Game game;

    @BeforeEach
    public void setUp() {
        game = new Game("p1", "p2");
    }

    @Test
    public void testCommand() {
        Car c1 = game.getPlayer1().getCar();
        Car c2 = game.getPlayer2().getCar();
        game.command(KeyEvent.VK_KP_LEFT);
        assertEquals(270, c2.getDirection());
        game.command(KeyEvent.VK_LEFT);
        assertEquals(180, c2.getDirection());
        game.command(KeyEvent.VK_RIGHT);
        assertEquals(270, c2.getDirection());
        game.command(KeyEvent.VK_KP_RIGHT);
        assertEquals(360, c2.getDirection());
        game.command(KeyEvent.VK_A);
        assertEquals(270, c1.getDirection());
        game.command(KeyEvent.VK_D);
        assertEquals(360, c1.getDirection());
        for (int i = 0; i < Game.BOARD_Y; i++) {
            game.doGameCycle();
        }
        game.command(KeyEvent.VK_R);
        assertFalse(game.isEnd());
        assertEquals(0, game.getWalls().size());
        assertEquals(new ArrayList<>(), game.getWalls());
    }
}
