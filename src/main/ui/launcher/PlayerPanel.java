package main.ui.launcher;

import javax.swing.*;

public class PlayerPanel extends JPanel {

    private JLabel instruction;
    private JTextField input;

    public PlayerPanel(String instruction) {
        setUpElements(instruction);
    }

    //MODIFIES: this
    //EFFECTS: set up elements in this panel
    private void setUpElements(String ins) {
        instruction = new JLabel(ins);
        input = new JTextField();

        add(instruction);
        add(input);

        instruction.setVisible(true);
        input.setVisible(true);
    }

    //EFFECTS: return user input in input
    public String getInput() {
        return input.getText();
    }
}
