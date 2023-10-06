package Entities;

import java.util.ArrayList;
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
                    try {
                        Piece piece2 = board.getPiece(rowEat, columnEat);

                        if (piece2 == null) {
                            position.setRow(rowEat);
                            position.setColumn(columnEat);
                            diagonalPossibilities.add(position);
                        }
                    } catch (Exception ignored) {
                        break;
                    }
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


}
