package Entities;

import java.sql.SQLOutput;
import java.util.*;

public class Table {
    private static final Piece[][] matrix = new Piece[8][8];


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

        Position positionTryed = positionTryedParam;
        int newRow = positionTryed.getRow();
        int newColumn = positionTryed.getColumn();

        if (arrayPositions.get(0).getRow() == newRow && arrayPositions.get(0).getColumn() == newColumn) {
            Piece piece = this.getPiece(rowPiece-1, columnPiece-1);
            choosedPiece.setPosition(positionTryed);
            if (piece != null) {
                String teamDied = piece.team;
                matrix[rowPiece-1][columnPiece-1] = null;
                if (Objects.equals(teamDied, "white")) {
                    return "white";
                } else {
                    return "black";
                }
            }
            return "Não comeu";
        } else if (arrayPositions.get(1).getRow() == newRow && arrayPositions.get(1).getColumn() == newColumn) {
            Piece piece = this.getPiece(rowPiece-1, columnPiece+1);
            choosedPiece.setPosition(positionTryed);
            if (piece != null) {
                String teamDied = piece.team;
                matrix[rowPiece-1][columnPiece+1] = null;
                if (Objects.equals(teamDied, "white")) {
                    return "white";
                } else {
                    return "black";
                }
            }

            return "Não comeu";
        } else if (arrayPositions.get(2).getRow() == newRow && arrayPositions.get(2).getColumn() == newColumn) {
            Piece piece = this.getPiece(rowPiece+1, columnPiece-1);
            choosedPiece.setPosition(positionTryed);
            if (piece != null) {
                String teamDied = piece.team;
                matrix[rowPiece+1][columnPiece-1] = null;
                if (Objects.equals(teamDied, "white")) {
                    return "white";
                } else {
                    return "black";
                }
            }

            return "Não comeu";
        } else if (arrayPositions.get(3).getRow() == newRow && arrayPositions.get(3).getColumn() == newColumn) {
            Piece piece = this.getPiece(rowPiece+1, columnPiece+1);
            choosedPiece.setPosition(positionTryed);
            if (piece != null) {
                String teamDied = piece.team;
                matrix[rowPiece+1][columnPiece+1] = null;
                if (Objects.equals(teamDied, "white")) {
                    return "white";
                } else {
                    return "black";
                }
            }

            return "Não comeu";
        } else {
            System.out.println("Escolha uma opção válida");
        }

        return "Deu alguma coisa errado";
    }
    public ArrayList<Position> getAllPossibilities(int rowPiece, int columnPiece) {
        ArrayList<Position> arrayPositions = new ArrayList<Position>() ;

        Piece choosedPiece = this.getPiece(rowPiece, columnPiece);
        Position position1 = new Position();
        Position position2 = new Position();
        Position position3 = new Position();
        Position position4 = new Position();

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    position1 = this.tryPossibilities(rowPiece, columnPiece, "NEGATIVE", "NEGATIVE");
                    arrayPositions.add(position1);
                    break;
                case 1:
                    position2 = this.tryPossibilities(rowPiece, columnPiece, "NEGATIVE", "POSITIVE");
                    arrayPositions.add(position2);
                    break;
                case 2:
                    position3 = this.tryPossibilities(rowPiece, columnPiece, "POSITIVE", "NEGATIVE");
                    arrayPositions.add(position3);
                    break;
                case 3:
                    position4 = this.tryPossibilities(rowPiece, columnPiece, "POSITIVE", "POSITIVE");
                    arrayPositions.add(position4);
                    break;
            }
        }

        return arrayPositions;
    }

    public Position tryPossibilities(int rowPiece, int columnPiece, String operationRow, String operationColumn) {
        Piece choosedPiece = this.getPiece(rowPiece, columnPiece);
        Position position = new Position();
        int rowPlay;
        int rowEat;
        int columnPlay;
        int columnEat;

        if (operationRow == "NEGATIVE") {
            rowPlay = rowPiece-1;
            rowEat = rowPiece-2;
        } else {
            rowPlay = rowPiece+1;
            rowEat = rowPiece+2;
        }

        if (operationColumn == "NEGATIVE") {
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
        return "" ;
    }
}
