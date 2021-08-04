package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//this is the main class
public class DeathRace extends JFrame {
    public static final int INTERVAL = 1;

    private Game game;
    private GamePanel gp;
    private ScorePanel sp;
    private Timer timer;

    //EFFECTS: initialize the frame
    public DeathRace() {
        super("DeathRace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        game = new Game();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        //add(sp);
        pack();
        centerOnScreen();
        setVisible(true);
        addKeyListener(new KeyHandler());
        addTimer();
        timer.start();
    }

    //MODIFIES: this
    //EFFECTS: centers the frame on screen
    private void centerOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    //MODIFIES: this
    //EFFECTS: initialize timer
    private void addTimer() {
        timer = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.doGameCycle();
                gp.repaint();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: receive keyEvents
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.command(e.getKeyCode());
        }
    }

    public static void main(String[] args) {
        new DeathRace();
    }
}
