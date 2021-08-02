package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    int MAX_PLAYER_AMOUNT = 2;
    int BOARD_X = 1000;
    int BOARD_Y = 1000;
    boolean end;
    List<Player> players;
    List<Position> walls;

    public Game(){
        setUp();
        while (!end) {
            if (end) {
                endGame();
            }
            doGameCycle();
        }
    }

    private void setUp(){

        players = new ArrayList<Player>();
        end = false;
        for (int i = 0; i < MAX_PLAYER_AMOUNT; i++) {
            Scanner playerName = new Scanner(System.in);
            int num = i + 1;
            System.out.println("enter player’s name for player " + num + " ：");
            if (playerName.hasNextLine()) {
                players.add(new Player(playerName.nextLine(), 0, 0, 0));
            } else {
                players.add(new Player("no name", 0, 0, 0));
            }
            System.out.println("player instantiated!");
        }
        walls = new ArrayList<Position>();
    }

    private void doGameCycle(){
        move();
        checkCollision();
    }

    private void move(){
        for (int i = 0; i < MAX_PLAYER_AMOUNT; i++) {
            players.get(i).getCar().move();
        }
    }

    private void checkCollision(){
        int numCollided = 0;
        for (int i = 0; i < MAX_PLAYER_AMOUNT; i++) {
            Car c = players.get(i).getCar();
            if (c.getPos().getX() > BOARD_X || c.getPos().getY() > BOARD_Y || walls.contains(c.getPos())) {
                c.setCollided();
                numCollided++;
            }
        }
        if (MAX_PLAYER_AMOUNT - numCollided == 1 || MAX_PLAYER_AMOUNT == numCollided) {
            end = true;
        }
    }

    private void endGame() {
        boolean hasWinner = false;
        for (int i = 0; i < MAX_PLAYER_AMOUNT; i++) {
            Player p = players.get(i);
            if (!p.getCar().isCollided()){
                System.out.println(p.getName() + " won!");
                p.addOneToScore();
                hasWinner = true;
            }
        }
        if (!hasWinner) {
            System.out.println("Nobody won");
        }
    }
}
