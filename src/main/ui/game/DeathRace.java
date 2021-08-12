package main.ui.game;

import main.model.Game;
import main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//this is the main class
public class DeathRace extends JFrame {
    public static final int INTERVAL = 1;

    private Game game;
    private GamePanel gp;
    private ScorePanel sp;
    private Timer timer;
    private ArrayList<Player> playerList;

    //EFFECTS: initialize the frame
    public DeathRace(String name1, String name2, ArrayList<Player> playerList) {
        super("DeathRace");
        this.playerList = playerList;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        Player p1 = handlePlayer(name1);
        Player p2 = handlePlayer(name2);
        game = new Game(p1, name1, p2, name2);
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.NORTH);
        pack();
        centerOnScreen();
        setVisible(true);
        addKeyListener(new KeyHandler());
        addTimer();
        timer.start();
    }

    //EFFECTS: return player with name name in playerList, null if such player do not exist
    private Player handlePlayer(String name) {
        for (Player p : playerList) {
            if (p.getName() == name) {
                return p;
            }
        }
        return null;
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
                    gp.handleInstructionDisplay();
                    sp.update();
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
}
