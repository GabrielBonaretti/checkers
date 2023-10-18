package Entities;

import Interfaces.PawnInterface;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The `Pawn` class represents a game piece of type "Pawn" in a board game.
 * It extends the `Piece` class and implements the `PawnInterface`.
 */
public class Pawn extends Piece implements PawnInterface {
    /**
     * Constructor for the `Pawn` class.
     *
     * @param name      The name of the pawn piece.
     * @param position  The initial position of the pawn piece on the game board.
     * @param team      The team to which the pawn piece belongs ("white" or "black").
     */
    public Pawn(String name, Position position, String team) {
        super(name, position, team);
    }

    /**
     * Handles a user's attempt to make a move with the pawn piece and updates the game board accordingly.
     *
     * @param turn              The current turn's team ("white" or "black").
     * @param arrayPositions    An `ArrayList` of possible move positions for the pawn piece.
     * @param positionTryedParam The position the user is attempting to move the pawn piece to.
     * @param board             The game board on which the pawn piece is placed.
     * @return A response string indicating the result of the move attempt.
     */
    public String userPlay(String turn, ArrayList<Position> arrayPositions, Position positionTryedParam, Table board) {
        String response;
        int countPiecesEat = 0;

        if (!Objects.equals(this.team, turn)) {
            return "NãoéSuaVez! " + countPiecesEat;
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
            System.out.println("Escolhaumaopçãoválida");
        }

        return "Deualgumacoisaerrado";
    }

    /**
     * Tries to make a move with the pawn piece and handle piece eating in a specific direction.
     *
     * @param positionTryedParam The position the user is attempting to move the pawn piece to.
     * @param operationRow       The direction of row movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param operationColumn    The direction of column movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param board              The game board on which the pawn piece is placed.
     * @return A response string indicating the result of the move attempt.
     */
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
                return "black "+1;
            } else {
                return "white "+1;
            }
        }
        return "NãoComeu "+0;
    }

    /**
     * Retrieves all possible move positions for the pawn piece, considering the game board's current state.
     *
     * @param board The game board on which the pawn piece is placed.
     * @return An `ArrayList` of `Position` objects representing possible move positions.
     */
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

    /**
     * Tries different move possibilities in various directions and returns a `Position` object.
     *
     * @param operationRow    The direction of row movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param operationColumn The direction of column movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param board           The game board on which the pawn piece is placed.
     * @return A `Position` object representing a possible move position.
     */
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
                if (Objects.equals(this.team, "black")) {
                    if (rowPlay == this.getPositionRow()-1 && columnPlay == this.getPositionColumn()-1 || rowPlay == this.getPositionRow()-1 && columnPlay == this.getPositionColumn()+1) {
                        position.setRow(rowPlay);
                        position.setColumn(columnPlay);
                    }
                } else {
                    if (rowPlay == this.getPositionRow()+1 && columnPlay == this.getPositionColumn()-1 || rowPlay == this.getPositionRow()+1 && columnPlay == this.getPositionColumn()+1) {
                        position.setRow(rowPlay);
                        position.setColumn(columnPlay);
                    }
                }

            }
        } catch (Exception ignored) {}

        return position;
    }

    /**
     * Checks if the pawn piece can make another move to eat an opponent's piece.
     *
     * @param arrayListPosition An `ArrayList` of possible move positions for the pawn piece.
     * @param row              The row of the piece being considered for eating.
     * @param column           The column of the piece being considered for eating.
     * @return `true` if the pawn piece can eat again in the current turn; otherwise, `false`.
     */
    public boolean eatAgain(ArrayList<Position> arrayListPosition, int row, int column) {
        for (Position positionOption : arrayListPosition) {
            if (Math.abs(positionOption.getRow()-row) == 2 && Math.abs(positionOption.getColumn()-column) == 2) {
                return true;
            }
        }
        return false;
    }
}
