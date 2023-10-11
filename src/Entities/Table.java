package Entities;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Table {
    public Piece[][] matrix = new Piece[8][8];


    public Table() {
        creatingPieces();
    }

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

    public Piece getPiece(int row, int column) {
        return matrix[row][column];
    }

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
