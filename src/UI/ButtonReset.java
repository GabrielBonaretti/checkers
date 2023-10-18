package UI;

import javax.swing.*;

/**
 * The `ButtonReset` class represents a graphical user interface button used to reset the game.
 * It provides a button with the label "RESET GAME" for initiating a game reset.
 */
public class ButtonReset extends JButton {
    /**
     * Constructs a new `ButtonReset` instance with the label "RESET GAME." The button is designed to initiate a game reset.
     */
    public ButtonReset() {
        super();
        this.setText("RESET GAME");
        this.setFocusable(false);
        this.setBounds(470, 0, 115, 30);
    }
}
