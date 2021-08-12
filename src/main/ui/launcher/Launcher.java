package main.ui.launcher;

import main.model.Game;
import main.model.Player;
import main.persistence.JsonReader;
import main.ui.game.DeathRace;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher extends JFrame {

    public static final String READ_FAIL = "Unable to read from " + JsonReader.SOURCE;
    public static final String NAME1 = "Please enter name for player 1: ";
    public static final String NAME2 = "Please enter name for player 2: ";


    private JLabel warning;
    private JsonReader reader;
    private ArrayList<Player> playerList;
    private PlayerPanel p1Panel;
    private PlayerPanel p2Panel;
    private JButton enter;

    public Launcher() {
        super("DeathRace Launcher");
        frameSetUp();
        reader = new JsonReader();
        read();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: centers the frame on screen
    private void centerOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    //MODIFIES: this
    //EFFECTS: read playerList from userData.json
    private void read() {
        try {
            playerList = reader.read();
        } catch (IOException exception) {
            showWarning(READ_FAIL);
            playerList = new ArrayList<>();
        }
    }

    //MODIFIES: this
    //EFFECTS: set text of warning to ins and set warning visible
    private void showWarning(String ins) {
        warning.setText(ins);
        warning.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: set up elements in mainFrame
    //TODO: finish this method
    private void frameSetUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        centerOnScreen();
        setLayout(new BorderLayout());

        p1Panel = new PlayerPanel(NAME1);
        p2Panel = new PlayerPanel(NAME2);
        enter = new JButton("continue");
        warning = new JLabel();

        warning.setVisible(false);
        enter.addActionListener(e -> {
            setVisible(false);
            new DeathRace(p1Panel.getInput(), p2Panel.getInput(), playerList);
        });
        enter.setBounds(Game.BOARD_X / 2 - 15, Game.BOARD_Y / 2 - 5, 30, 10);

        add(warning);
        add(p1Panel, BorderLayout.WEST);
        add(p2Panel, BorderLayout.EAST);
        add(enter);
    }

    public static void main(String[] args) {
        new Launcher();
    }
}
