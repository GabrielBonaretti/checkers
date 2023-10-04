package ScreenEntities;

import javax.swing.*;

public class Screen {
    private final JFrame frame;
    private Board gridPanel;
    private final Status statusPanel;

    public Screen() {
        frame = new JFrame("DAMA DO BONA!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setResizable(false);
        frame.setLayout(null);

        statusPanel = new Status();
        gridPanel = new Board();

        startGame();

        frame.add(statusPanel);
        frame.add(gridPanel);
        frame.setVisible(true);
    }

    private void startGame() {
        gridPanel.createBoard(frame, statusPanel);
    }

}

