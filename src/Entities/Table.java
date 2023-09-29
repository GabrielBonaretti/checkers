package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    private static final Piece[][] matrix = new Piece[8][8];
    private int numberWhitePieces = 12;
    private int numberBlackPieces = 12;

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
                    Piece piece = new Piece("PAWN"+count, position, team);

                    if (i % 2 == 0 && j % 2 == 1 ) {
                        matrix[i][j] = piece;

                    } else if (i % 2 == 1 && j % 2 == 0) {
                        matrix[i][j] = piece;
                    }

                    count += 1;
                }
            }
        }
    }

    public void alocatingBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix[i][j] != null) {
                    Piece piece = matrix[i][j];
                    int rowPiece = piece.position.getRow();
                    int collumnPiece = piece.position.getColumn();

                    if (i != rowPiece && j != collumnPiece) {
                        matrix[rowPiece][collumnPiece] = piece;
                        matrix[i][j] = null;
                    }
                }
            }
        }
    }

    public Piece getPiece(int row, int column) {
        return matrix[row][column];
    }

    public void verifyPlay(int rowPiece, int columnPiece, int newRow, int newColumn){
        Piece choosedPiece = this.getPiece(rowPiece, columnPiece);
        ArrayList<Map<String, Object>> leftUpDiagonal = getLeftUpDiagonals(rowPiece, columnPiece);
        ArrayList<Map<String, Object>> rightUpDiagonal = getRightUpDiagonals(rowPiece, columnPiece);
        ArrayList<Map<String, Object>> rightDownDiagonal = getRightDownDiagonals(rowPiece, columnPiece);
        ArrayList<Map<String, Object>> leftDownDiagonal = getLeftDownDiagonals(rowPiece, columnPiece);
        choosedPiece.changePosition(
                leftUpDiagonal,
                rightUpDiagonal,
                rightDownDiagonal,
                leftDownDiagonal,
                newRow,
                newColumn
        );
    }

    public ArrayList<Map<String, Object>> getLeftUpDiagonals(int row, int column) {
        ArrayList<Map<String, Object>> leftUpList = new ArrayList<>();

        // min(row) - min(column)
        for (int i = row; i >= 0; i--){
            for (int j = column; j >= 0; j--){
                int diferenceX = i - row;
                int diferenceY = j - column;
                if ( Math.abs(diferenceY) == Math.abs(diferenceX)) {
                    Map<String, Object> leftUp = new HashMap<String, Object>();
                    leftUp.put("row", String.valueOf(i));
                    leftUp.put("column", String.valueOf(i));
                    leftUp.put("piece", matrix[i][j]);

                    leftUpList.add(leftUp);
                }
            }
        }

        return leftUpList;
    }

    public ArrayList<Map<String, Object>> getRightUpDiagonals(int row, int column) {
        ArrayList<Map<String, Object>> rightUpList = new ArrayList<>();

        // min(row) - max(column)
        for (int i = row; i >= 0; i--){
            for (int j = column; j < 8; j++){
                int diferenceX = i - row;
                int diferenceY = j - column;
                if ( Math.abs(diferenceY) == Math.abs(diferenceX)) {
                    Map<String, Object> rightUp = new HashMap<String, Object>();
                    rightUp.put("row", String.valueOf(i));
                    rightUp.put("column", String.valueOf(i));
                    rightUp.put("piece", matrix[i][j]);

                    rightUpList.add(rightUp);
                }
            }
        }

        return rightUpList;
    }

    public ArrayList<Map<String, Object>> getRightDownDiagonals(int row, int column) {
        ArrayList<Map<String, Object>> rightDownList = new ArrayList<>();

        // max(row) - min(column)
        for (int i = row; i < 8; i++){
            for (int j = column; j >= 0; j--){
                int diferenceX = i - row;
                int diferenceY = j - column;
                if ( Math.abs(diferenceY) == Math.abs(diferenceX)) {
                    Map<String, Object> rightDown = new HashMap<String, Object>();
                    rightDown.put("row", String.valueOf(i));
                    rightDown.put("column", String.valueOf(i));
                    rightDown.put("piece", matrix[i][j]);

                    rightDownList.add(rightDown);
                }
            }
        }

        return rightDownList;
    }

    public ArrayList<Map<String, Object>> getLeftDownDiagonals(int row, int column) {
        ArrayList<Map<String, Object>> leftDownList = new ArrayList<>();

        // max(row) - max(column)
        for (int i = row; i < 8; i++){
            for (int j = column; j < 8; j++){
                int diferenceX = i - row;
                int diferenceY = j - column;
                if ( Math.abs(diferenceY) == Math.abs(diferenceX)) {
                    Map<String, Object> leftDown = new HashMap<String, Object>();
                    leftDown.put("row", String.valueOf(i));
                    leftDown.put("column", String.valueOf(i));
                    leftDown.put("piece", matrix[i][j]);

                    leftDownList.add(leftDown);
                }
            }
        }

        return leftDownList;
    }

    @Override
    public String toString() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("| %15s ",  matrix[i][j]);
            }
            System.out.println();
        }
        return "" ;
    }
}
