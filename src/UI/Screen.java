package UI;

import javax.swing.*;

/**
 * The `Screen` class represents the graphical user interface of the game "DAMA DO BONA."
 * It sets up the game window, including the game board, status panel, and reset button.
 */
public class Screen {
    private final JFrame frame;
    private final Board gridPanel;
    private final Status statusPanel;

    /**
     * Constructs a new `Screen` instance, initializing the game window, components, and starting the game.
     */
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

    /**
     * Initializes and configures the game window, including its size, title, layout, and components.
     */
    private void startGame() {
        gridPanel.createBoard(frame, statusPanel);
    }

    /**
     * Resets the game to its initial state by calling the corresponding method in the game board.
     */
    private void resetGame() {
        gridPanel.resetBoard(frame, statusPanel);
    }
}

