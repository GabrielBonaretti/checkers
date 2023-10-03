package Entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Tela {
    public static void main(String[] args) {
        JFrame frame = new JFrame("8x8 Grid Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel gridPanel = new JPanel(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Botao cell = new Botao(i, j);
                System.out.println(gridPanel);
                if (i != 3 && i != 4) {
                    Position position = new Position(i, j);
                    String team = "white";

                    if (i > 4) {
                        team = "black";
                    }
                    Piece piece = new Piece("", position, team);
                    ImageIcon pawnWhiteImage = new ImageIcon("C:\\Users\\ct67ca\\Desktop\\dama\\src\\images\\pawnWhite.png");
                    pawnWhiteImage.setImage(pawnWhiteImage.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));

                    ImageIcon pawnBlackImage = new ImageIcon("C:\\Users\\ct67ca\\Desktop\\dama\\src\\images\\pawnBlack.png");
                    pawnBlackImage.setImage(pawnBlackImage.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));

                    if (i % 2 == 0 && j % 2 == 1 ) {
                        cell.setPiece(piece);

                        if (team.equals("white")) {
                            cell.setIcon(pawnWhiteImage);
                        } else {
                            cell.setIcon(pawnBlackImage);
                        }
                    } else if (i % 2 == 1 && j % 2 == 0) {
                        cell.setPiece(piece);

                        if (team.equals("white")) {
                            cell.setIcon(pawnWhiteImage);
                        } else {
                            cell.setIcon(pawnBlackImage);
                        }
                    }

                }
                cell.addActionListener(e -> System.out.println(cell));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(cell);
            }
        }

        frame.add(gridPanel);
        frame.setVisible(true);
    }
}

