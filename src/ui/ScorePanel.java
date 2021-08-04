package ui;

import model.Game;

import javax.swing.*;

//TODO: implement this class
//This class is the score panel, in charge of drawing the score of each player
public class ScorePanel extends JPanel {

    private Game game;

    public ScorePanel(Game game) {
        this.game = game;
    }
}
