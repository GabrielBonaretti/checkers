package Entities;

import java.util.*;

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
                    Piece piece = new Piece("", position, team);

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

    public String userPlay(String turn, ArrayList<Position> arrayPositions, Position positionTryedParam, int rowPiece, int columnPiece) {
        Piece choosedPiece = this.getPiece(rowPiece, columnPiece);

        if (!Objects.equals(choosedPiece.team, turn)) {
            return "Não é sua vez!";
        }

        int newRow = positionTryedParam.getRow();
        int newColumn = positionTryedParam.getColumn();


        if (arrayPositions.get(0).getRow() == newRow && arrayPositions.get(0).getColumn() == newColumn) {
            return tryPlay(choosedPiece, positionTryedParam,"NEGATIVE", "NEGATIVE", rowPiece, columnPiece);
        } else if (arrayPositions.get(1).getRow() == newRow && arrayPositions.get(1).getColumn() == newColumn) {
            return tryPlay(choosedPiece, positionTryedParam,"NEGATIVE", "POSITIVE", rowPiece, columnPiece);
        } else if (arrayPositions.get(2).getRow() == newRow && arrayPositions.get(2).getColumn() == newColumn) {
            return tryPlay(choosedPiece, positionTryedParam,"POSITIVE", "NEGATIVE", rowPiece, columnPiece);
        } else if (arrayPositions.get(3).getRow() == newRow && arrayPositions.get(3).getColumn() == newColumn) {
            return tryPlay(choosedPiece, positionTryedParam,"POSITIVE", "POSITIVE", rowPiece, columnPiece);
        } else {
            System.out.println("Escolha uma opção válida");
        }

        return "Deu alguma coisa errado";
    }

    public String tryPlay(Piece choosedPiece, Position positionTryedParam, String operationRow, String operationColumn, int rowPiece, int columnPiece) {
        int rowPlay;
        int columnPlay;

        if (Objects.equals(operationRow, "NEGATIVE")) {
            rowPlay = rowPiece-1;
        } else {
            rowPlay = rowPiece+1;
        }

        if (Objects.equals(operationColumn, "NEGATIVE")) {
            columnPlay = columnPiece-1;
        } else {
            columnPlay = columnPiece+1;
        }

        Piece piece = this.getPiece(rowPlay, columnPlay);
        choosedPiece.setPosition(positionTryedParam);
        if (piece != null) {
            String teamDied = piece.team;
            matrix[rowPlay][columnPlay] = null;
            if (Objects.equals(teamDied, "white")) {
                return "white";
            } else {
                return "black";
            }
        }
        return "Não comeu";
    }

    public ArrayList<Position> getAllPossibilities(int rowPiece, int columnPiece) {
        ArrayList<Position> arrayPositions = new ArrayList<Position>() ;

        Position position1;
        Position position2;
        Position position3;
        Position position4;

        position1 = this.tryPossibilities(rowPiece, columnPiece, "NEGATIVE", "NEGATIVE");
        arrayPositions.add(position1);

        position2 = this.tryPossibilities(rowPiece, columnPiece, "NEGATIVE", "POSITIVE");
        arrayPositions.add(position2);

        position3 = this.tryPossibilities(rowPiece, columnPiece, "POSITIVE", "NEGATIVE");
        arrayPositions.add(position3);

        position4 = this.tryPossibilities(rowPiece, columnPiece, "POSITIVE", "POSITIVE");
        arrayPositions.add(position4);

        return arrayPositions;
    }

    public Position tryPossibilities(int rowPiece, int columnPiece, String operationRow, String operationColumn) {
        Piece choosedPiece = this.getPiece(rowPiece, columnPiece);
        Position position = new Position();
        int rowPlay;
        int rowEat;
        int columnPlay;
        int columnEat;

        if (Objects.equals(operationRow, "NEGATIVE")) {
            rowPlay = rowPiece-1;
            rowEat = rowPiece-2;
        } else {
            rowPlay = rowPiece+1;
            rowEat = rowPiece+2;
        }

        if (Objects.equals(operationColumn, "NEGATIVE")) {
            columnPlay = columnPiece-1;
            columnEat = columnPiece-2;
        } else {
            columnPlay = columnPiece+1;
            columnEat = columnPiece+2;
        }

        try {
            Piece piece = this.getPiece(rowPlay, columnPlay);

            if (piece != null && !Objects.equals(piece.team, choosedPiece.team)) {
                try {
                    Piece piece2 = this.getPiece(rowEat, columnEat);

                    if (piece2 == null) {
                        position.setRow(rowEat);
                        position.setColumn(columnEat);
                    }
                } catch (Exception ignored) {}
            } else if (piece == null) {
                position.setRow(rowPlay);
                position.setColumn(columnPlay);
            }
        } catch (Exception ignored) {}

        return position;
    }
}
