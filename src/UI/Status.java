package UI;

import javax.swing.*;
import java.awt.*;

/**
 * The `Status` class represents a graphical user interface component that displays the game's status information.
 * It provides information about the current turn and the number of black and white pieces in the game.
 */
public class Status extends JLabel {

    /**
     * Constructs a new `Status` instance that displays game status information. It sets the position, text color,
     * font, border, and initial status message.
     */
    public Status() {
        this.setBounds(0,0,470,30);
        this.setForeground(new Color(30, 30,30));
        this.setFont(new Font("Arial", Font.BOLD,20));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setText("Turn: black | Black pieces: 12 - White pieces: 12");
    }
}