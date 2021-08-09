package main.ui;

import main.model.*;

import javax.swing.*;
import java.awt.*;

//This class is the game panel, in charge of drawing the game
public class GamePanel extends JPanel {

    public static final String REPLAY = "R to replay";
    public static final String CONGRATULATE_WINNER = "The winner is ";
    public static final String NO_WINNER = "Nobody won";

    private JLabel replay;

    private Game game;

    //EFFECTS: initialize the game panel
    public GamePanel(Game game) {
        setPreferredSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        setBackground(Color.PINK);
        replaySetUp();
        add(replay);
        this.game = game;
    }

    private void replaySetUp() {
        replay = new JLabel(REPLAY);
        replay.setVisible(false);
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
    //EFFECTS: displays at the end of the game
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        FontMetrics fm = g.getFontMetrics();
        replay.setVisible(true);
        g.setColor(saved);
    }

    //MODIFIES: this
    //EFFECTS: set visibility of instructions to false if game is started
    public void handleInstructionDisplay() {
        if (!game.isEnd()) {
            replay.setVisible(false);
        }
    }
}
