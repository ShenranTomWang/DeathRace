package main.ui.game;

import main.model.*;

import javax.swing.*;

//This class is the score panel, in charge of drawing the score of each player
public class ScorePanel extends JPanel {

    private Game game;
    private JLabel player1;
    private JLabel player2;

    //EFFECTS: set this.game to game and set up labels
    public ScorePanel(Game game) {
        this.game = game;
        setUpLabels();
        setVisible(true);
        add(player1);
        add(Box.createHorizontalStrut(10));
        add(player2);
    }

    //MODIFIES: this
    //EFFECTS: set up JLabels
    private void setUpLabels() {
        player1 = new JLabel(game.getPlayer1().getName() + ": " + game.getPlayer1().getScore());
        player1.setBounds(5, 5, 20, 10);
        player2 = new JLabel(game.getPlayer2().getName() + ": " + game.getPlayer2().getScore());
        player2.setBounds(Game.BOARD_X - 25, 5, 20, 10);

    }

    //MODIFIES: this
    //EFFECTS: update info on JLabels
    public void update() {
        player1.setText(game.getPlayer1().getName() + ": " + game.getPlayer1().getScore());
        player2.setText(game.getPlayer2().getName() + ": " + game.getPlayer2().getScore());
    }
}
