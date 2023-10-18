package Entities;

import Interfaces.LadyInterface;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The `Lady` class represents a game piece of type "Lady" in a board game.
 * It extends the `Piece` class and implements the `LadyInterface`.
 */
public class Lady extends Piece implements LadyInterface {

    /**
     * Constructor for the `Lady` class.
     *
     * @param name      The name of the lady piece.
     * @param position  The initial position of the lady piece on the game board.
     * @param team      The team to which the lady piece belongs ("white" or "black").
     */
    public Lady(String name, Position position, String team) {
        super(name, position, team);
    }

    /**
     * Retrieves all possible move positions for the lady piece, considering the game board's current state.
     *
     * @param board The game board on which the lady piece is placed.
     * @return An `ArrayList` of `Position` objects representing possible move positions.
     */
    public ArrayList<Position> getAllPossibilities(Table board) {
        ArrayList<Position> arrayPositions = new ArrayList<Position>() ;

        ArrayList<Position> diagonalLeftUp;
        ArrayList<Position> diagonalRightUp;
        ArrayList<Position> diagonalLeftDown;
        ArrayList<Position> diagonalRightDown;


        diagonalLeftUp = this.tryPossibilities("NEGATIVE", "NEGATIVE", board);
        arrayPositions.addAll(diagonalLeftUp);

        diagonalRightUp = this.tryPossibilities("NEGATIVE", "POSITIVE", board);
        arrayPositions.addAll(diagonalRightUp);

        diagonalLeftDown = this.tryPossibilities("POSITIVE", "NEGATIVE", board);
        arrayPositions.addAll(diagonalLeftDown);

        diagonalRightDown = this.tryPossibilities("POSITIVE", "POSITIVE", board);
        arrayPositions.addAll(diagonalRightDown);

        return arrayPositions;
    }

    /**
     * Tries different move possibilities in a specific direction, considering the game board's state.
     *
     * @param operationRow    The direction of row movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param operationColumn The direction of column movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param board           The game board on which the lady piece is placed.
     * @return An `ArrayList` of `Position` objects representing possible move positions in a specific direction.
     */
    public ArrayList<Position> tryPossibilities(String operationRow, String operationColumn, Table board) {
        ArrayList<Position> diagonalPossibilities = new ArrayList<Position>();
        int countSequencePieces = 0;
        int rowPlay;
        int rowEat;
        int columnPlay;
        int columnEat;

        int count = 1;
        while (true) {
            Position position = new Position();
            if (Objects.equals(operationRow, "NEGATIVE")) {
                rowPlay = this.getPositionRow()-count;
                rowEat = this.getPositionRow()-(count+1);
            } else {
                rowPlay = this.getPositionRow()+count;
                rowEat = this.getPositionRow()+(count+1);
            }

            if (Objects.equals(operationColumn, "NEGATIVE")) {
                columnPlay = this.getPositionColumn()-count;
                columnEat = this.getPositionColumn()-(count+1);
            } else {
                columnPlay = this.getPositionColumn()+count;
                columnEat = this.getPositionColumn()+(count+1);
            }

            try {
                Piece piece = board.getPiece(rowPlay, columnPlay);

                if (piece != null && !Objects.equals(piece.team, this.team)) {
                    countSequencePieces++;
                    if (countSequencePieces > 1) {
                        break;
                    }

                    try {
                        Piece piece2 = board.getPiece(rowEat, columnEat);

                        if (piece2 == null) {
                            countSequencePieces--;
                            position.setRow(rowEat);
                            position.setColumn(columnEat);
                            diagonalPossibilities.add(position);
                        }
                    } catch (Exception ignored) {
                        break;
                    }
                } else if (piece != null && Objects.equals(piece.team, this.team)) {
                    break;
                } else if (piece == null) {
                    position.setRow(rowPlay);
                    position.setColumn(columnPlay);
                    diagonalPossibilities.add(position);
                }
            } catch (Exception ignored) {
                break;
            }
            count ++;
        }



        return diagonalPossibilities;
    }

    /**
     * Handles a user's attempt to make a move with the lady piece and updates the game board accordingly.
     *
     * @param turn              The current turn's team ("white" or "black").
     * @param arrayPositions    An `ArrayList` of possible move positions for the lady piece.
     * @param positionTryedParam The position the user is attempting to move the lady piece to.
     * @param board             The game board on which the lady piece is placed.
     * @return A response string indicating the result of the move attempt.
     */
    public String userPlay(String turn, ArrayList<Position> arrayPositions, Position positionTryedParam, Table board) {
        String response;
        int countPiecesEat = 0;

        if (!Objects.equals(this.team, turn)) {
            return "NãoéSuaVez! " + countPiecesEat;
        }

        int difRow = this.getPositionRow()-positionTryedParam.getRow();
        int difColumn = this.getPositionColumn()-positionTryedParam.getColumn();


        if (difRow < 0 && difColumn < 0) {
            for (int i = 1; i <= Math.abs(difRow); i++) {
                Piece piece = board.getPiece(this.getPositionRow() + i, this.getPositionColumn() + i);
                if (piece != null) {
                    board.matrix[this.getPositionRow() + i][this.getPositionColumn() + i] = null;
                    countPiecesEat += 1;
                }
            }
        } else if (difRow > 0 && difColumn < 0) {
            for (int i = 1; i <= Math.abs(difRow); i++) {
                Piece piece = board.getPiece(this.getPositionRow() - i, this.getPositionColumn() + i);
                if (piece != null) {
                    board.matrix[this.getPositionRow() - i][this.getPositionColumn() + i] = null;
                    countPiecesEat += 1;
                }
            }
        } else if (difRow < 0 && difColumn > 0) {
            for (int i = 1; i <= Math.abs(difRow); i++) {
                Piece piece = board.getPiece(this.getPositionRow() + i, this.getPositionColumn() - i);
                if (piece != null) {
                    board.matrix[this.getPositionRow() + i][this.getPositionColumn() - i] = null;
                    countPiecesEat += 1;
                }
            }
        } else if (difRow > 0 && difColumn > 0) {
            for (int i = 1; i <= Math.abs(difRow); i++) {
                Piece piece = board.getPiece(this.getPositionRow() - i, this.getPositionColumn() - i);
                if (piece != null) {
                    board.matrix[this.getPositionRow() - i][this.getPositionColumn() - i] = null;
                    countPiecesEat += 1;

                }
            }
        }

        if (Objects.equals(this.team, "white")) {
            response = "white "+countPiecesEat;
        } else {
            response = "black "+countPiecesEat;
        }

        for (Position position : arrayPositions) {
            if (position.getRow() == positionTryedParam.getRow() && position.getColumn() == positionTryedParam.getColumn()) {
                this.setPosition(positionTryedParam);
            }
        }

        return response;
    }

    /**
     * Checks if the lady piece can make another move to eat an opponent's piece.
     *
     * @param arrayListPosition An `ArrayList` of possible move positions for the lady piece.
     * @param row              The row of the piece being considered for eating.
     * @param column           The column of the piece being considered for eating.
     * @return `true` if the lady piece can eat again in the current turn; otherwise, `false`.
     */
    public boolean eatAgain(ArrayList<Position> arrayListPosition, int row, int column) {
        int rowTest = 10;
        int columnTest = 10;
        for (Position positionOption : arrayListPosition) {
            if (rowTest == positionOption.getRow() && columnTest == positionOption.getColumn()) {
                return true;
            } else {
                rowTest = positionOption.getRow();
                columnTest = positionOption.getColumn();
            }
        }
        return false;
    }


}
