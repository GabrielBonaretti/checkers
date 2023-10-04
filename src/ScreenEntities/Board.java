package ScreenEntities;

import Entities.Piece;
import Entities.Position;
import Entities.Table;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Board extends JPanel {
    private ArrayList<Position> optionList = new ArrayList<>();
    private final Table board = new Table();
    private String turn = "black";
    private int numberWhitePieces = 12;
    private int numberBlackPieces = 12;
    private int columnPiece;
    private int rowPiece;

    public int getNumberWhitePieces() {
        return numberWhitePieces;
    }

    public void setNumberWhitePieces(int numberWhitePieces) {
        this.numberWhitePieces = numberWhitePieces;
    }

    public int getNumberBlackPieces() {
        return numberBlackPieces;
    }

    public void setNumberBlackPieces(int numberBlackPieces) {
        this.numberBlackPieces = numberBlackPieces;
    }

    public Board() {
        super(new GridLayout(8, 8));
        this.setBounds(-3, 49, 390, 363);

    }

    public void createBoard(JFrame frame, Status statusPanel) {
        this.removeAll(); // Clear the existing board
        board.alocatingBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i, j);
                ScreenEntities.Button buttonCell = new ScreenEntities.Button(position);
                buttonCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (board.matrix[i][j] != null) {
                    if (Objects.equals(board.matrix[i][j].team, "black")) {
                        buttonCell.setPiece(board.matrix[i][j]);
                    } else {
                        buttonCell.setPiece(board.matrix[i][j]);
                    }
                }

                buttonCell.setImagesButton();

                buttonCell.addActionListener(e -> this.selectPossibilities(buttonCell.position.getRow(), buttonCell.position.getColumn(), frame, statusPanel));

                this.add(buttonCell);
            }
        }

        frame.revalidate(); // Revalidate the frame to update the layout
        frame.repaint();    // Repaint the frame to reflect changes
    }

    public ScreenEntities.Button getButtonByXAndY(int x, int y) {
        try {
            return (ScreenEntities.Button) this.getComponent(x * 8 + y);
        } catch(Exception e) {
            return null;
        }
    }

    public void selectPossibilities(int row, int column, JFrame frame, Status statusPanel) {
        ScreenEntities.Button buttonClicked = getButtonByXAndY(row, column);

        if (buttonClicked.piece != null) {
            clearPossibilities();

            this.optionList = board.getAllPossibilities(row, column);

            for (Position option : optionList) {
                ScreenEntities.Button optionButton = getButtonByXAndY(option.getRow(), option.getColumn());
                if (optionButton != null) {
                    optionButton.setBorder(new LineBorder(Color.YELLOW));
                    optionButton.setPossiblePlay(true);
                }
            }

            this.rowPiece = row;
            this.columnPiece = column;
        } else if (buttonClicked.isPossiblePlay()) {
            boolean eatAgain = false;
            Position positionTryed = new Position(row, column);
            String response = board.userPlay(this.turn, this.optionList, positionTryed, this.rowPiece, this.columnPiece);

            clearPossibilities();
            updateBoardMatrix(frame, statusPanel);

            ArrayList<Position> arrayListPosition = board.getAllPossibilities(row, column);
            for (Position positionOption : arrayListPosition) {
                if (Math.abs(positionOption.getRow()-row) == 2 && Math.abs(positionOption.getColumn()-column) == 2) {
                    eatAgain = true;
                }
            }



            if (Objects.equals(response, "Não é sua vez!")) {
                System.out.println(response);
            } else if (Objects.equals(response, "white") && !eatAgain) {
                this.numberWhitePieces -= 1;
                this.turn = "white";
            } else if (Objects.equals(response, "black") && !eatAgain) {
                this.numberBlackPieces -= 1;
                this.turn = "black";
            } else if (Objects.equals(response, "Não comeu")) {
                if (Objects.equals(this.turn, "white")) {
                    this.turn = "black";
                } else {
                    this.turn = "white";
                }
            } else if (Objects.equals(response, "white") && eatAgain || Objects.equals(response, "black") && eatAgain) {
                Piece piece = board.getPiece(row, column);

                if (Objects.equals(piece.team, "white")) {
                    this.numberBlackPieces -= 1;
                } else {
                    this.numberWhitePieces -= 1;
                }
            }
        }

        statusPanel.setText("Turn: " + this.turn + " | " + "Black pieces: " + this.numberBlackPieces + " - White pieces: " + this.numberWhitePieces );
    }

    // Method to update the board matrix
    public void updateBoardMatrix(JFrame frame, Status statusPanel) {
        this.verifyEnd();
        board.alocatingBoard();
        this.createBoard(frame, statusPanel); // Refresh the board layout
    }

    public void clearPossibilities() {
        for (int i = 0; i < 64; i++) {
            ScreenEntities.Button button = (Button) this.getComponent(i);
            button.setBorder(new LineBorder(Color.BLACK));
            button.setPossiblePlay(false);
        }
    }

    public void verifyEnd() {
        if ( numberWhitePieces == 0 || numberBlackPieces == 0 ) {
            board.creatingPieces();
        }
    }
}
