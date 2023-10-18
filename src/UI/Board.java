package UI;

import Entities.Lady;
import Entities.Piece;
import Entities.Position;
import Entities.Table;
import Entities.Pawn;
import Interfaces.PieceInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The `Board` class represents the graphical user interface for a board game. It handles the game board's
 * visual representation, user interactions, and game logic.
 */
public class Board extends JPanel {
    private ArrayList<Position> optionList = new ArrayList<>();
    private final Table board = new Table();
    private String turn = "black";
    private int numberWhitePieces = 12;
    private int numberBlackPieces = 12;
    private int columnPiece;
    private int rowPiece;

    /**
     * Sets the number of white pieces on the board.
     *
     * @param numberWhitePieces The number of white pieces.
     */
    public void setNumberWhitePieces(int numberWhitePieces) {
        this.numberWhitePieces = numberWhitePieces;
    }

    /**
     * Sets the number of black pieces on the board.
     *
     * @param numberBlackPieces The number of black pieces.
     */
    public void setNumberBlackPieces(int numberBlackPieces) {
        this.numberBlackPieces = numberBlackPieces;
    }

    /**
     * Constructs a new `Board` instance, initializing the game board's visual representation.
     */
    public Board() {
        super(new GridLayout(8, 8));
        this.setBounds(-3, 29, 590, 563);

    }

    /**
     * Creates and updates the graphical representation of the game board within the provided frame.
     *
     * @param frame        The main game frame.
     * @param statusPanel  The status panel for displaying game information.
     */
    public void createBoard(JFrame frame, Status statusPanel) {
        this.removeAll(); // Clear the existing board

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i, j);
                UI.Button buttonCell = new UI.Button(position);
                buttonCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (board.matrix[i][j] != null) {
                    if (Objects.equals(board.matrix[i][j].team, "black")) {
                        buttonCell.setPiece(board.matrix[i][j]);
                    } else {
                        buttonCell.setPiece(board.matrix[i][j]);
                    }
                }

                buttonCell.setImagesButton();

                buttonCell.addActionListener(e -> this.selectPossibilities(buttonCell.position.getRow(), buttonCell.position.getColumn(), frame, statusPanel));

                this.add(buttonCell);
            }
        }

        frame.revalidate(); // Revalidate the frame to update the layout
        frame.repaint();    // Repaint the frame to reflect changes
    }

    /**
     * Retrieves a button element on the game board based on its row and column indices.
     *
     * @param x The row index.
     * @param y The column index.
     * @return The `UI.Button` element corresponding to the specified row and column, or `null` if not found.
     */
    public UI.Button getButtonByXAndY(int x, int y) {
        try {
            return (UI.Button) this.getComponent(x * 8 + y);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Handles user interactions when a game piece is selected, highlighting possible move positions and updating the board.
     *
     * @param row          The row of the selected game piece.
     * @param column       The column of the selected game piece.
     * @param frame        The main game frame.
     * @param statusPanel  The status panel for displaying game information.
     */
    public void selectPossibilities(int row, int column, JFrame frame, Status statusPanel) {
        UI.Button buttonClicked = getButtonByXAndY(row, column);


        PieceInterface pieceTest;
        try {
            pieceTest = (Pawn) board.getPiece(row, column);
        } catch (Exception e) {
            pieceTest = (Lady) board.getPiece(row, column);
        }

        if (pieceTest != null) {
            clearPossibilities();


            this.optionList = pieceTest.getAllPossibilities(board);


            for (Position option : optionList) {
                UI.Button optionButton = getButtonByXAndY(option.getRow(), option.getColumn());
                if (optionButton != null) {
                    optionButton.setBorder(new LineBorder(Color.YELLOW, 3));
                    optionButton.setPossiblePlay(true);
                }
            }

            this.rowPiece = row;
            this.columnPiece = column;
        } else if (buttonClicked.isPossiblePlay()) {
            Position positionTryed = new Position(row, column);
            try {
                pieceTest = (Pawn) board.getPiece(rowPiece, columnPiece);
            } catch (Exception e) {
                pieceTest = (Lady) board.getPiece(rowPiece, columnPiece);
            }


            String response = pieceTest.userPlay(this.turn, this.optionList, positionTryed, board);


            String[] responseArray = response.split(" ");
            int quantPiecesEat = Integer.parseInt(responseArray[1]);
            clearPossibilities();
            updateBoardMatrix(frame, statusPanel);


            ArrayList<Position> arrayListPosition = pieceTest.getAllPossibilities(board);
            boolean eatAgain = pieceTest.eatAgain(arrayListPosition, row, column);

            if (responseArray[0].equals("NãoéSuaVez!")) {
                System.out.println(response);
            } else if (Objects.equals(responseArray[0], "white") && quantPiecesEat > 0 && !eatAgain) {
                this.numberBlackPieces -= quantPiecesEat;
                this.turn = "black";
            } else if (Objects.equals(responseArray[0], "black") && quantPiecesEat > 0 && !eatAgain) {
                this.numberWhitePieces -= quantPiecesEat;
                this.turn = "white";
            } else if (quantPiecesEat == 0) {
                if (Objects.equals(this.turn, "white")) {
                    this.turn = "black";
                } else {
                    this.turn = "white";
                }
            } else if (Objects.equals(responseArray[0], "white") && eatAgain || Objects.equals(responseArray[0], "black") && eatAgain) {
                Piece piece = board.getPiece(row, column);

                if (Objects.equals(piece.team, "white")) {
                    this.numberBlackPieces -= quantPiecesEat;
                } else {
                    this.numberWhitePieces -= quantPiecesEat;
                }
            }
        }

        statusPanel.setText("Turn: " + this.turn + " | " + "Black pieces: " + this.numberBlackPieces + " - White pieces: " + this.numberWhitePieces );
    }

    /**
     * Updates the game board's matrix, handling piece allocation, promotion, and board layout refresh.
     *
     * @param frame        The main game frame.
     * @param statusPanel  The status panel for displaying game information.
     */
    public void updateBoardMatrix(JFrame frame, Status statusPanel) {
        board.alocatingBoard();
        board.verifyPromotion();
        this.createBoard(frame, statusPanel); // Refresh the board layout
    }

    /**
     * Clears the highlighting of possible move positions on the game board.
     */
    public void clearPossibilities() {
        for (int i = 0; i < 64; i++) {
            UI.Button button = (Button) this.getComponent(i);
            button.setBorder(new LineBorder(Color.BLACK));
            button.setPossiblePlay(false);
        }
    }

    /**
     * Resets the game board to its initial state, including piece allocation, turn reset, and piece count.
     *
     * @param frame        The main game frame.
     * @param statusPanel  The status panel for displaying game information.
     */
    public void resetBoard(JFrame frame, Status statusPanel) {
        board.creatingPieces();
        this.createBoard(frame, statusPanel);
        this.turn = "black";
        this.setNumberBlackPieces(12);
        this.setNumberWhitePieces(12);
        statusPanel.setText("Turn: " + this.turn + " | " + "Black pieces: " + this.numberBlackPieces + " - White pieces: " + this.numberWhitePieces );
    }

}
