package UI;

import Entities.Lady;
import Entities.Pawn;
import Entities.Piece;
import Entities.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The `Button` class represents a graphical user interface button used to display a game piece on the game board.
 * It handles the visual representation and behavior of a game piece button, including image loading, color settings,
 * and interactivity.
 */
public class Button extends JButton {
    public Piece piece;
    public boolean possiblePlay = false;
    public Position position;

    /**
     * Constructs a new `Button` instance associated with a specific position on the game board.
     *
     * @param position The `Position` object representing the button's position on the board.
     */
    public Button(Position position) {
        super();
        this.position = position;
        this.setFocusable(false);
        colorButton();
    }

    /**
     * Sets the image for the button based on the game piece associated with it. The image is resized to a specific
     * dimension for consistent visual representation.
     */
    public void setImagesButton() {
        ImageIcon pawnWhiteImage = new ImageIcon("src/images/pawnWhite.png");
        ImageIcon pawnBlackImage = new ImageIcon("src/images/pawnBlack.png");
        if (piece != null) {
            if (piece instanceof Lady) {
                pawnWhiteImage = new ImageIcon("src/images/ladyWhite.png");
                pawnBlackImage = new ImageIcon("src/images/ladyBlack.png");
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

    /**
     * Sets the background color of the button based on its position on the game board. Buttons representing squares
     * with even row and column indices are set to white, while buttons representing squares with odd row and column
     * indices are set to a darker gray color.
     */
    public void colorButton() {
        if (position.getRow() % 2 == 0 && position.getColumn() % 2 == 0) {
            this.setBackground(Color.WHITE);
        } else if (position.getRow() % 2 == 1 && position.getColumn() % 2 == 1) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(new Color(60, 60, 60));
        }
    }

    /**
     * Checks if the button represents a possible move position for a game piece.
     *
     * @return `true` if the button represents a possible move position; otherwise, `false`.
     */
    public boolean isPossiblePlay() {
        return possiblePlay;
    }

    /**
     * Sets whether the button represents a possible move position for a game piece.
     *
     * @param possiblePlay `true` to indicate a possible move position; `false` to indicate otherwise.
     */
    public void setPossiblePlay(boolean possiblePlay) {
        this.possiblePlay = possiblePlay;
    }

    /**
     * Sets the game piece associated with the button, allowing it to display the piece's image and team.
     *
     * @param piece The game piece associated with the button.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }


}
