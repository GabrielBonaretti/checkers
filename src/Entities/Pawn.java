package Entities;

import UI.Board;

import java.util.ArrayList;
import java.util.Objects;

public class Pawn extends Piece{
    public Pawn(String name, Position position, String team) {
        super(name, position, team);
    }

    public String userPlay(String turn, ArrayList<Position> arrayPositions, Position positionTryedParam, Table board) {
        if (!Objects.equals(this.team, turn)) {
            return "Não é sua vez!";
        }

        int newRow = positionTryedParam.getRow();
        int newColumn = positionTryedParam.getColumn();


        if (arrayPositions.get(0).getRow() == newRow && arrayPositions.get(0).getColumn() == newColumn) {
            return tryPlay(positionTryedParam,"NEGATIVE", "NEGATIVE", board);
        } else if (arrayPositions.get(1).getRow() == newRow && arrayPositions.get(1).getColumn() == newColumn) {
            return tryPlay(positionTryedParam,"NEGATIVE", "POSITIVE", board);
        } else if (arrayPositions.get(2).getRow() == newRow && arrayPositions.get(2).getColumn() == newColumn) {
            return tryPlay(positionTryedParam,"POSITIVE", "NEGATIVE", board);
        } else if (arrayPositions.get(3).getRow() == newRow && arrayPositions.get(3).getColumn() == newColumn) {
            return tryPlay(positionTryedParam,"POSITIVE", "POSITIVE", board);
        } else {
            System.out.println("Escolha uma opção válida");
        }

        return "Deu alguma coisa errado";
    }

    public String tryPlay(Position positionTryedParam, String operationRow, String operationColumn, Table board) {
        int rowPlay;
        int columnPlay;

        if (Objects.equals(operationRow, "NEGATIVE")) {
            rowPlay = this.getPositionRow()-1;
        } else {
            rowPlay = this.getPositionRow()+1;
        }

        if (Objects.equals(operationColumn, "NEGATIVE")) {
            columnPlay = this.getPositionColumn()-1;
        } else {
            columnPlay = this.getPositionColumn()+1;
        }

        Piece piece = board.getPiece(rowPlay, columnPlay);
        this.setPosition(positionTryedParam);
        if (piece != null) {
            String teamDied = piece.team;
            board.matrix[rowPlay][columnPlay] = null;
            if (Objects.equals(teamDied, "white")) {
                return "white";
            } else {
                return "black";
            }
        }
        return "Não comeu";
    }

    public ArrayList<Position> getAllPossibilities(Table board) {
        ArrayList<Position> arrayPositions = new ArrayList<Position>() ;
        Position position1;
        Position position2;
        Position position3;
        Position position4;

        position1 = this.tryPossibilities("NEGATIVE", "NEGATIVE", board);
        arrayPositions.add(position1);

        position2 = this.tryPossibilities("NEGATIVE", "POSITIVE", board);
        arrayPositions.add(position2);

        position3 = this.tryPossibilities("POSITIVE", "NEGATIVE", board);
        arrayPositions.add(position3);

        position4 = this.tryPossibilities("POSITIVE", "POSITIVE", board);
        arrayPositions.add(position4);

        return arrayPositions;
    }

    public Position tryPossibilities(String operationRow, String operationColumn, Table board) {
        Position position = new Position();
        int rowPlay;
        int rowEat;
        int columnPlay;
        int columnEat;

        if (Objects.equals(operationRow, "NEGATIVE")) {
            rowPlay = this.getPositionRow()-1;
            rowEat = this.getPositionRow()-2;
        } else {
            rowPlay = this.getPositionRow()+1;
            rowEat = this.getPositionRow()+2;
        }

        if (Objects.equals(operationColumn, "NEGATIVE")) {
            columnPlay = this.getPositionColumn()-1;
            columnEat = this.getPositionColumn()-2;
        } else {
            columnPlay = this.getPositionColumn()+1;
            columnEat = this.getPositionColumn()+2;
        }

        try {
            Piece piece = board.getPiece(rowPlay, columnPlay);

            if (piece != null && !Objects.equals(piece.team, this.team)) {
                try {
                    Piece piece2 = board.getPiece(rowEat, columnEat);

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
