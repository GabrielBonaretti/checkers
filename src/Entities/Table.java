package Entities;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * The `Table` class represents the game board in a board game. It maintains a matrix of game pieces
 * and provides various methods to manipulate and manage the game state.
 */
public class Table {
    /**
     * A 2D array representing the game board, where each cell may contain a game piece or be null.
     */
    public Piece[][] matrix = new Piece[8][8];

    /**
     * Constructor for the `Table` class. Initializes the game board by creating and placing game pieces.
     */
    public Table() {
        creatingPieces();
    }

    /**
     * Fills the game board matrix with game pieces according to game rules.
     */
    public void creatingPieces() {
        // Fill the matrix with values
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i != 3 && i != 4) {
                    Position position = new Position(i, j);
                    String team = "white";

                    if (i > 4) {
                        team = "black";
                    }
                    Pawn piece = new Pawn("", position, team);

                    if (i % 2 == 0 && j % 2 == 1 ) {
                        matrix[i][j] = piece;

                    } else if (i % 2 == 1 && j % 2 == 0) {
                        matrix[i][j] = piece;
                    }

                    count += 1;
                } else {
                    matrix[i][j] = null;
                }
            }
        }
    }

    /**
     * Allocates game pieces on the board based on their current positions.
     * Handles potential promotions from Pawn to Lady.
     */
    public void alocatingBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix[i][j] != null) {
                    Pawn pawn = null;
                    Lady lady = null;
                    
                    try{
                        pawn = (Pawn) matrix[i][j];
                    } catch (Exception ignored) {
                        lady = (Lady) matrix[i][j];
                    }
                    
                    if (pawn != null) {
                        if (i != pawn.getPositionRow() && j != pawn.getPositionColumn()) {
                            matrix[pawn.getPositionRow()][pawn.getPositionColumn()] = pawn;
                            matrix[i][j] = null;
                        }
                    } else if (lady != null) {
                        if (i != lady.getPositionRow() && j != lady.getPositionColumn()) {
                            matrix[lady.getPositionRow()][lady.getPositionColumn()] = lady;
                            matrix[i][j] = null;
                        }
                    }

                }
            }
        }
    }

    /**
     * Retrieves the game piece at a specified position on the board.
     *
     * @param row    The row of the position.
     * @param column The column of the position.
     * @return The game piece at the specified position or `null` if the position is empty.
     */
    public Piece getPiece(int row, int column) {
        return matrix[row][column];
    }

    /**
     * Checks for promotions of Pawns to Ladies and updates the board accordingly.
     */
    public void verifyPromotion() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i, j);
                if (i == 0 && matrix[i][j] != null && Objects.equals(matrix[i][j].team, "black")) {
                    matrix[i][j] = new Lady("", position, "black");
                } else if (i == 7 && matrix[i][j] != null && Objects.equals(matrix[i][j].team, "white")) {
                    matrix[i][j] = new Lady("", position, "white");
                }
            }
        }
    }
}
