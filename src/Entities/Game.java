package Entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int numberWhitePieces = 12;
    private int numberBlackPieces = 12;
    public String turn = "black";

    public int countTurn  = 0;

    public Game() {gameGoing();}

    public void gameGoing() {
        Scanner sc = new Scanner(System.in);
        String response;
        ArrayList<Position> arrayListPosition;
        Table board = new Table();
        System.out.println(board);

        while (numberBlackPieces > 0 || numberBlackPieces > 0) {
            System.out.println("É a vez das peças " + turn);
            System.out.println("Digite a linha da peça: ");
            int row = sc.nextInt();

            System.out.println("Digite a coluna da peça: ");
            int column = sc.nextInt();

            arrayListPosition = board.getAllPossibilities(row, column);

            System.out.println(arrayListPosition);


            System.out.println("Digite a linha: ");
            int newRow = sc.nextInt();

            System.out.println("Digite a coluna: ");
            int newColumn = sc.nextInt();
            Position placeTryed = new Position(newRow, newColumn);
            response = board.userPlay(turn, arrayListPosition, placeTryed, row, column);

            arrayListPosition = board.getAllPossibilities(newRow, newColumn);
            System.out.println(arrayListPosition);
            for (Position positionOption : arrayListPosition) {
                if (Math.abs(positionOption.getRow()-newRow) == 2 && Math.abs(positionOption.getColumn()-newColumn) == 2) {
                    response = "Pode comer de novo";
                }
            }

            if (response == "Não é sua vez!") {
                System.out.println(response);
            } else if (response == "white") {
                this.numberWhitePieces -= 1;
                this.turn = "black";
            } else if (response == "black") {
                this.numberBlackPieces -= 1;
                this.turn = "white";
            } else if (response == "Não comeu") {
                if (this.turn == "white") {
                    this.turn = "black";
                } else {
                    this.turn = "white";
                }
            }


            board.alocatingBoard();

            System.out.println(board);
        }
    }
}
