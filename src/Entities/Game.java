package Entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    private int numberWhitePieces = 12;
    private int numberBlackPieces = 12;
    public String turn = "black";
    public Piece currentPiece = null;

    public Game() {gameGoing();}

    public void gameGoing() {
        Scanner sc = new Scanner(System.in);
        Table board = new Table();

        ArrayList<Position> arrayListPosition;
        String response;

        int column = 0;
        boolean eatAgain = false;
        int row = 0;


        System.out.println(board);

        while (numberBlackPieces > 0 || numberWhitePieces > 0) {
            eatAgain = false;

            System.out.println("Turn: " + turn);

            if (currentPiece == null) {
                System.out.println("Digite a linha da peça: ");
                row = sc.nextInt();

                System.out.println("Digite a coluna da peça: ");
                column = sc.nextInt();

                arrayListPosition = board.getAllPossibilities(row, column);
            } else {
                row = currentPiece.position.getRow();
                column = currentPiece.position.getColumn();

                arrayListPosition = board.getAllPossibilities(row, column);
            }

            System.out.println(arrayListPosition);

            System.out.println("Digite a linha: ");
            int newRow = sc.nextInt();

            System.out.println("Digite a coluna: ");
            int newColumn = sc.nextInt();

            Position placeTryed = new Position(newRow, newColumn);
            response = board.userPlay(turn, arrayListPosition, placeTryed, row, column);

            board.alocatingBoard();

            arrayListPosition = board.getAllPossibilities(newRow, newColumn);
            for (Position positionOption : arrayListPosition) {
                if (Math.abs(positionOption.getRow()-newRow) == 2 && Math.abs(positionOption.getColumn()-newColumn) == 2) {
                    eatAgain = true;
                }
            }

            verifyEndTurn(eatAgain, response, newRow, newColumn, board);


            System.out.println(board);
        }

        sc.close();
    }

    public void verifyEndTurn(boolean eatAgain, String response, int newRow, int newColumn, Table board) {
        if (Objects.equals(response, "Não é sua vez!")) {
            System.out.println(response);
        } else if (Objects.equals(response, "white") && !eatAgain) {
            this.numberWhitePieces -= 1;
            this.turn = "white";
            this.currentPiece = null;
        } else if (Objects.equals(response, "black") && !eatAgain) {
            this.numberBlackPieces -= 1;
            this.turn = "black";
            this.currentPiece = null;
        } else if (Objects.equals(response, "Não comeu")) {
            if (Objects.equals(this.turn, "white")) {
                this.turn = "black";
            } else {
                this.turn = "white";
            }
            this.currentPiece = null;
        } else if (Objects.equals(response, "white") && eatAgain || Objects.equals(response, "black") && eatAgain) {
            this.currentPiece = board.getPiece(newRow, newColumn);

            if (Objects.equals(currentPiece.team, "white")) {
                this.numberBlackPieces -= 1;
            } else {
                this.numberWhitePieces -= 1;
            }
        }
    }
}
