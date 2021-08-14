package main.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//This class represents the game, in charge of managing data of the game
public class Game {

    public static final int BOARD_X = 500;
    public static final int BOARD_Y = 500;

    private boolean end;
    private Player player1;
    private Player player2;
    private List<Position> walls;
    private Player winner;

    //EFFECTS: do setup and start game
    public Game(String name1, String name2){
        Random rand = new Random();
        player1 = new Player(name1, rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y), 0, new Color(rand.nextInt()));
        player2 = new Player(name2, rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y), 0, new Color(rand.nextInt()));
        winner = null;
        end = false;
        walls = new ArrayList<>();
    }

    //EFFECTS: do setup and start game
    public Game(Player p1, String name1, Player p2, String name2) {
        Random rand = new Random();
        player1 = p1;
        player2 = p2;
        if (p1 == null) {
            player1 = new Player(name1, 0, new Color(rand.nextInt()));
        }
        if (p2 == null) {
            player2 = new Player(name2, 0, new Color(rand.nextInt()));
        }
        winner = null;
        end = false;
        walls = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: do one cycle of game
    public void doGameCycle(){
        move();
        checkCollision();
    }

    //MODIFIES: this
    //EFFECTS: move all players' car
    private void move(){
        List<Position> w1 = player1.getCar().move();
        List<Position> w2 = player2.getCar().move();
        walls.addAll(w1);
        walls.addAll(w2);
    }

    //MODIFIES: this
    //EFFECTS: check whether collisions happened, stop collided cars, and if all collided / only one car left, end game
    private void checkCollision(){
        Car c1 = player1.getCar();
        Car c2 = player2.getCar();
        if (checkCollided(c1) && checkCollided(c2)) {
            c1.setCollided();
            c2.setCollided();
            winner = null;
            end = true;
        } else if (checkCollided(c2)) {
            c2.setCollided();
            c1.stop();
            winner = player1;
            end = true;
            player1.addOneToScore();
        } else if (checkCollided(c1)) {
            c1.setCollided();
            c2.stop();
            winner = player2;
            end = true;
            player2.addOneToScore();
        }
    }

    //EFFECTS: return true if car is collided
    private boolean checkCollided(Car c) {
        return c.getPos().getX() > BOARD_X || c.getPos().getY() > BOARD_Y || walls.contains(c.getPos())
                || c.getPos().getX() < 0 || c.getPos().getY() < 0;
    }

    //MODIFIES: this
    //EFFECTS: deal with key inputs
    public void command(int keyCode) {
        Car c1 = player1.getCar();
        Car c2 = player2.getCar();
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            c2.turnLeft();
        } else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            c2.turnRight();
        } else if (keyCode == KeyEvent.VK_A) {
            c1.turnLeft();
        } else if (keyCode == KeyEvent.VK_D) {
            c1.turnRight();
        } else if (keyCode == KeyEvent.VK_R && end) {
            reset();
        }
    }

    public void draw(Graphics g) {
        for (Position p : walls) {
            p.draw(g);
        }
        player1.getCar().draw(g);
        player2.getCar().draw(g);
    }

    //MODIFIES: this
    //EFFECTS: reset game data
    private void reset() {
        Random rand = new Random();
        player1.resetCar(new Position(rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y)), 0);
        player2.resetCar(new Position(rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y)), 0);
        walls = new ArrayList<>();
        end = false;
        winner = null;
    }

    //getters

    public boolean isEnd() {
        return end;
    }

    public List<Position> getWalls() {
        return walls;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
