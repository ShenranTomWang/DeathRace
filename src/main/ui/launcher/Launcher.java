package main.ui.launcher;

import main.model.Game;
import main.model.Player;
import main.persistence.JsonReader;
import main.ui.game.DeathRace;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

public class Launcher extends JFrame {

    public static final String READ_FAIL = "Unable to read from " + JsonReader.SOURCE;
    public static final String NAME1 = "Please enter name for player 1: ";
    public static final String NAME2 = "Please enter name for player 2: ";

    private JLabel warning;
    private JsonReader reader;
    private HashSet<Player> playerList;
    private JPanel playerPanel;
    private JButton enter;
    private JLabel instruction1;
    private JLabel instruction2;
    private JTextField input1;
    private JTextField input2;

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
            playerList = new HashSet<>();
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
    private void frameSetUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(Game.BOARD_X, Game.BOARD_Y));
        centerOnScreen();
        setLayout(new BorderLayout());
        playerPanelSetUp();

        add(playerPanel, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: set up playerPanel
    private void playerPanelSetUp() {
        playerPanel = new JPanel();
        setUpElements();
        playerPanel.setBorder(BorderFactory.createEmptyBorder());
        playerPanel.setLayout(null);
    }

    //MODIFIES: this
    //EFFECTS: set up elements in this panel
    private void setUpElements() {
        instruction1 = new JLabel(NAME1);
        instruction2 = new JLabel(NAME2);
        input1 = new JTextField();
        input2 = new JTextField();
        enter = new JButton("continue");
        warning = new JLabel();

        instruction1.setBounds(25, 5, 200, 30);
        input1.setBounds(25, 50, 150, 25);
        instruction2.setBounds(Game.BOARD_X - 225, 5, 200, 30);
        input2.setBounds(Game.BOARD_X - 225, 50, 150, 25);
        enter.addActionListener(e -> {
            setVisible(false);
            new DeathRace(input1.getText(), input2.getText(), playerList);
        });
        enter.setBounds(Game.BOARD_X / 2 - 50, Game.BOARD_Y - 100, 100, 35);
        warning.setVisible(false);

        playerPanel.add(instruction1);
        playerPanel.add(input1);
        playerPanel.add(instruction2);
        playerPanel.add(input2);
        playerPanel.add(warning);
        playerPanel.add(enter);
    }

    public static void main(String[] args) {
        new Launcher();
    }
}
