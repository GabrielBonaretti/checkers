package UI;

import Entities.Pawn;
import Entities.Piece;
import Entities.Position;
import Entities.Table;
import Entities.Lady;

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


    public void setNumberWhitePieces(int numberWhitePieces) {
        this.numberWhitePieces = numberWhitePieces;
    }

    public void setNumberBlackPieces(int numberBlackPieces) {
        this.numberBlackPieces = numberBlackPieces;
    }

    public Board() {
        super(new GridLayout(8, 8));
        this.setBounds(-3, 29, 590, 563);

    }

    public void createBoard(JFrame frame, Status statusPanel) {
        this.removeAll(); // Clear the existing board
        board.alocatingBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i, j);
                UI.Button buttonCell = new UI.Button(position);
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

    public UI.Button getButtonByXAndY(int x, int y) {
        try {
            return (UI.Button) this.getComponent(x * 8 + y);
        } catch(Exception e) {
            return null;
        }
    }

    public void selectPossibilities(int row, int column, JFrame frame, Status statusPanel) {
        UI.Button buttonClicked = getButtonByXAndY(row, column);
        Lady pieceTest = (Lady) board.getPiece(row, column);

        if (pieceTest != null) {
            clearPossibilities();
            this.optionList = pieceTest.getAllPossibilities(board);
            for (Position option : optionList) {
                UI.Button optionButton = getButtonByXAndY(option.getRow(), option.getColumn());
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
            pieceTest = (Lady) board.getPiece(rowPiece, columnPiece);
            String response = pieceTest.userPlay(this.turn, this.optionList, positionTryed, board);

            clearPossibilities();
            updateBoardMatrix(frame, statusPanel);

            ArrayList<Position> arrayListPosition = pieceTest.getAllPossibilities(board);
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
        board.alocatingBoard();
        this.createBoard(frame, statusPanel); // Refresh the board layout
    }

    public void clearPossibilities() {
        for (int i = 0; i < 64; i++) {
            UI.Button button = (Button) this.getComponent(i);
            button.setBorder(new LineBorder(Color.BLACK));
            button.setPossiblePlay(false);
        }
    }

    public void resetBoard(JFrame frame, Status statusPanel) {
        board.creatingPieces();
        this.createBoard(frame, statusPanel);
        this.turn = "black";
        this.setNumberBlackPieces(12);
        this.setNumberWhitePieces(12);
        statusPanel.setText("Turn: " + this.turn + " | " + "Black pieces: " + this.numberBlackPieces + " - White pieces: " + this.numberWhitePieces );
    }
}
