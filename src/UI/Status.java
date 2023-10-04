package UI;

import javax.swing.*;
import java.awt.*;

public class Status extends JLabel {
    public Status() {
        this.setBounds(0,0,470,30);
        this.setForeground(new Color(30, 30,30));
        this.setFont(new Font("Arial", Font.BOLD,20));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setText("Turn: black | Black pieces: 12 - White pieces: 12");
    }
}