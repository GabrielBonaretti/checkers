package Entities;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Table board = new Table();


        System.out.println(board);

        while(true) {
            System.out.println("Digite a linha da peça: ");
            int row = sc.nextInt();

            System.out.println("Digite a coluna da peça: ");
            int column = sc.nextInt();


            System.out.println("Digite a linha: ");
            int newRow = sc.nextInt();

            System.out.println("Digite a coluna: ");
            int newColumn = sc.nextInt();


            board.verifyPlay(row, column, newRow, newColumn);

            board.alocatingBoard();

            System.out.println(board);
        }


    }
}
