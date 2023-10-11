package UI;

import Entities.Lady;
import Entities.Pawn;
import Entities.Piece;
import Entities.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Button extends JButton {
    public Piece piece;
    public boolean possiblePlay = false;
    public Position position;

    public Button(Position position) {
        super();
        this.position = position;
        this.setFocusable(false);
        colorButton();
    }

    public void setImagesButton() {
        ImageIcon pawnWhiteImage = new ImageIcon("src/images/pawnWhite.png");
        ImageIcon pawnBlackImage = new ImageIcon("src/images/pawnBlack.png");
        if (piece != null) {
            if (piece instanceof Lady) {
                pawnWhiteImage = new ImageIcon("src/images/download.png");
                pawnBlackImage = new ImageIcon("src/images/download.png");
            }
        }
 
        pawnWhiteImage.setImage(pawnWhiteImage.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));

        pawnBlackImage.setImage(pawnBlackImage.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));

        if (this.piece != null) {
            if (Objects.equals(this.piece.team, "black")) {
                this.setIcon(pawnBlackImage);
            } else {
                this.setIcon(pawnWhiteImage);
            }
        }
    }

    public void colorButton() {
        if (position.getRow() % 2 == 0 && position.getColumn() % 2 == 0) {
            this.setBackground(Color.WHITE);
        } else if (position.getRow() % 2 == 1 && position.getColumn() % 2 == 1) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(new Color(30, 30, 30));
        }
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
