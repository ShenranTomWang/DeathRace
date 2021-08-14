package main.ui.game;

import main.model.Game;
import main.model.Player;
import main.persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.HashSet;

//this is the main class
//TODO: implement data storing in this class
public class DeathRace extends JFrame {
    public static final int INTERVAL = 1;
    public static final String SAVE_SUCCESSFUL = "data saved";
    public static final String SAVE_FAIL = "an error has occurred, unable to save data";

    private Game game;
    private GamePanel gp;
    private ScorePanel sp;
    private Timer timer;
    private HashSet<Player> playerList;

    //EFFECTS: initialize the frame
    public DeathRace(String name1, String name2, HashSet<Player> playerList) {
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
            if (p.getName().equals(name)) {
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
        timer = new Timer(INTERVAL, e -> {
                game.doGameCycle();
                gp.repaint();
                gp.handleInstructionDisplay();
                sp.update();
        });
    }

    //MODIFIES: this
    //EFFECTS: receive keyEvents
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_S && game.isEnd()) {
                save();
            }
            game.command(e.getKeyCode());
        }
    }

    //MODIFIES: this
    //EFFECTS: save data to JSON file
    private void save() {
        JsonWriter writer = new JsonWriter();
        if (!playerList.contains(game.getPlayer1())) {
            playerList.add(game.getPlayer1());
        }
        if (!playerList.contains(game.getPlayer2())) {
            playerList.add(game.getPlayer2());
        }
        try {
            writer.open();
            writer.write(playerList);
            writer.close();
            gp.displayWarning(SAVE_SUCCESSFUL);
        } catch (FileNotFoundException e) {
            gp.displayWarning(SAVE_FAIL);
        }
    }
}
