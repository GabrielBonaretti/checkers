package Entities;

import javax.swing.*;

public class Botao extends JButton {
    int row;
    int column;
    Piece piece;

    public Botao(int row, int column) {
        super();
        this.setSize(200, 100);
        this.setLocation(500, 600);
        this.row = row;
        this.column = column;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return this.row + ", " + this.column + "|" + piece;
    }
}
