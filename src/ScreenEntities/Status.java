package ScreenEntities;

import javax.swing.*;
import java.awt.*;

public class Status extends JLabel {
    public Status() {
        this.setBounds(0,0,400,50);
        this.setForeground(new Color(155, 155,155));
        this.setFont(new Font("Arial", Font.BOLD,17));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setText("Turn: black | Black pieces: 12  - White pieces: 12");
    }
}