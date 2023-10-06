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
                    Lady piece = new Lady("", position, team);

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

    @Override
    public String toString() {
        for (int n = 0; n < 8; n++) {
            System.out.printf("| %15d ",  n);
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(i);

            for (int j = 0; j < 8; j++) {
                System.out.printf("| %15s ",  matrix[i][j]);
            }
            System.out.println();
        }
        return super.toString();
    }
}
