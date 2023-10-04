package UI;

import javax.swing.*;

public class Screen {
    private final JFrame frame;
    private final Board gridPanel;
    private final Status statusPanel;

    public Screen() {
        frame = new JFrame("DAMA DO BONA!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 630);
        frame.setResizable(false);
        frame.setLayout(null);

        statusPanel = new Status();
        gridPanel = new Board();
        ButtonReset buttonReset = new ButtonReset();
        buttonReset.addActionListener(e -> this.resetGame());

        startGame();

        frame.add(statusPanel);
        frame.add(buttonReset);
        frame.add(gridPanel);
        frame.setVisible(true);
    }

    private void startGame() {
        gridPanel.createBoard(frame, statusPanel);
    }

    private void resetGame() {
        gridPanel.resetBoard(frame, statusPanel);
    }
}

