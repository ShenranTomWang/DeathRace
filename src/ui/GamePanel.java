package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

//This class is the game panel, in charge of drawing the game
public class GamePanel extends JPanel {

    public static final String REPLAY = "R to replay";

    private Game game;

    //EFFECTS: initialize the game panel
    public GamePanel(Game game) {
        setPreferredSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        setBackground(Color.PINK);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
        if (game.isEnd()) {
            gameOver(g);
        }
    }

    //MODIFIES: this
    //EFFECTS: displays at the end of the game & reset if needed
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        FontMetrics fm = g.getFontMetrics();
        displayString(REPLAY, g, fm, Game.BOARD_Y / 2 + 50);
        g.setColor(saved);
        game.endGame();
    }

    //EFFECTS: draw a string in the center of window
    private void displayString(String replay, Graphics g, FontMetrics fm, int i) {
        int width =fm.stringWidth(replay);
        g.drawString(replay, (Game.BOARD_X - width) / 2, i);
    }
}
