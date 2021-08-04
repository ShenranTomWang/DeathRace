package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//This class represents the game, in charge of managing data of the game
public class Game {

    public static final int BOARD_X = 500;
    public static final int BOARD_Y = 500;

    private boolean end;
    private Player player1;
    private Player player2;
    private List<Position> walls;

    //EFFECTS: do setup and start game
    public Game(){
        setUp();
    }

    //MODIFIES: this
    //EFFECTS: set up necessary data, initialize MAX_PLAYER_AMOUNT players at random positions
    private void setUp(){
        end = false;
        Random rand = new Random();
        Scanner playerName = new Scanner(System.in);
        System.out.println("enter player’s name for player 1: ");
        if (playerName.hasNextLine()) {
            player1 = new Player(playerName.nextLine(), rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y), 0, Color.BLUE);
        } else {
            player1 = new Player("no name", rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y), 0, Color.BLUE);
        }
        System.out.println("player instantiated!");
        System.out.println("enter player’s name for player 2: ");
        if (playerName.hasNextLine()) {
            player2 = new Player(playerName.nextLine(), rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y), 0, Color.GRAY);
        } else {
            player2 = new Player("no name", rand.nextInt(BOARD_X), rand.nextInt(BOARD_Y), 0, Color.GRAY);
        }
        System.out.println("player instantiated!");
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
        if (checkCollided(c1) || checkCollided(c2)) {
            c1.setCollided();
            c2.setCollided();
            end = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: return true if car is collided
    private boolean checkCollided(Car c) {
        return c.getPos().getX() > BOARD_X || c.getPos().getY() > BOARD_Y || walls.contains(c.getPos())
                || c.getPos().getX() < 0 || c.getPos().getY() < 0;
    }

    //EFFECTS: end game, congratulate winner (if any), add scores
    public void endGame() {
        if (!player1.getCar().isCollided()) {
            System.out.println(player1.getName() + " won!");
            player1.addOneToScore();
        } else if (!player2.getCar().isCollided()) {
            System.out.println(player2.getName() + " won!");
            player2.addOneToScore();
        } else {
            System.out.println("Nobody won");
        }
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
    }

    //getters

    public boolean isEnd() {
        return end;
    }


}
