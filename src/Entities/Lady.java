package Entities;

import Aplication.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Lady extends Piece{

    public Lady(String name, Position position, String team) {
        super(name, position, team);
    }

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
