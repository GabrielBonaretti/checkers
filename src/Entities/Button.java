package Entities;

import javax.swing.*;

public class Button extends JButton {
    public Piece piece;
    public boolean possiblePlay = false;
    public Position position;

    public Button(Position position) {
        super();
        this.setSize(200, 100);
        this.setLocation(500, 600);
        this.position = position;
    }

    public boolean isPossiblePlay() {
        return possiblePlay;
    }

    public void setPossiblePlay(boolean possiblePlay) {
        this.possiblePlay = possiblePlay;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }


}
