package main.ui.game;

import main.model.*;

import javax.swing.*;
import java.awt.*;

//This class is the game panel, in charge of drawing the game
public class GamePanel extends JPanel {

    public static final String REPLAY = "R to replay";
    public static final String SAVE = "S to save data";

    private JLabel replay;
    private JLabel save;
    private JLabel warning;

    private Game game;

    //EFFECTS: initialize the game panel
    public GamePanel(Game game) {
        setPreferredSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        setBackground(Color.PINK);
        replaySetUp();
        saveSetUp();
        warningSetUp();

        setBorder(BorderFactory.createEmptyBorder());
        setLayout(null);

        add(replay);
        add(save);
        add(warning);
        this.game = game;
    }

    //MODIFIES: this
    //EFFECTS: set up save
    private void saveSetUp() {
        save = new JLabel(SAVE);
        save.setBounds(Game.BOARD_X / 2 - 50, Game.BOARD_Y / 2 - 10, 100, 20);
        save.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: set up warning
    private void warningSetUp() {
        warning = new JLabel();
        warning.setBounds(Game.BOARD_X / 2 - 50, Game.BOARD_Y / 2 + 10, 100, 20);
        warning.setVisible(false);
    }

    public void displayWarning(String ins) {
        warning.setText(ins);
        warning.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: set up replay
    private void replaySetUp() {
        replay = new JLabel(REPLAY);
        replay.setBounds(Game.BOARD_X / 2 - 50, Game.BOARD_Y / 2 - 35, 100, 20);
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
        save.setVisible(true);
        g.setColor(saved);
    }

    //MODIFIES: this
    //EFFECTS: set visibility of instructions to false if game is started
    public void handleInstructionDisplay() {
        if (!game.isEnd()) {
            replay.setVisible(false);
            save.setVisible(false);
            warning.setVisible(false);
        }
    }
}
