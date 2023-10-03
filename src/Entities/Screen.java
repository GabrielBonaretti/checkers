package Entities;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Screen {
    private JFrame frame;
    private JPanel gridPanel;
    private Piece[][] boardMatrix;
    ArrayList<Position> optionList = new ArrayList<>();
    Table board = new Table();
    int rowPiece;
    int columnPiece;
    ImageIcon pawnWhiteImage;
    ImageIcon pawnBlackImage;
    private int numberWhitePieces = 12;
    private int numberBlackPieces = 12;
    public String turn = "black";
    public Piece currentPiece = null;

    public Screen() {
        frame = new JFrame("DAMA DO BONA!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        gridPanel = new JPanel(new GridLayout(8, 8));

        Table board = new Table();
        boardMatrix = board.matrix; // Initialize your board matrix here

        createBoard();

        frame.add(gridPanel);
        frame.setVisible(true);
    }

    private void createBoard() {
        gridPanel.removeAll(); // Clear the existing board
        board.alocatingBoard();

        boardMatrix = board.matrix;

        pawnWhiteImage = new ImageIcon("src/images/pawnWhite.png");
        pawnWhiteImage.setImage(pawnWhiteImage.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));

        pawnBlackImage = new ImageIcon("src/images/pawnBlack.png");
        pawnBlackImage.setImage(pawnBlackImage.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(i, j);
                Button cell = new Button(position);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (boardMatrix[i][j] != null) {
                    if (Objects.equals(boardMatrix[i][j].team, "black")) { // You can customize this condition based on your board state
                        cell.setIcon(pawnBlackImage);
                        cell.setPiece(boardMatrix[i][j]);
                    } else {
                        cell.setIcon(pawnWhiteImage);
                        cell.setPiece(boardMatrix[i][j]);
                    }
                }
                if (currentPiece != null) {
                    selectPossibilities(currentPiece.position.getRow(), currentPiece.position.getColumn());
                } else {
                    cell.addActionListener(e -> selectPossibilities(cell.position.getRow(), cell.position.getColumn()));
                }

                gridPanel.add(cell);
            }
        }

        frame.revalidate(); // Revalidate the frame to update the layout
        frame.repaint();    // Repaint the frame to reflect changes
    }

    public Button getButtonByXAndY(int x, int y) {
        try {
            return (Button) this.gridPanel.getComponent(x * 8 + y);
        } catch(Exception e) {
            return null;
        }
    }

    public void selectPossibilities(int row, int column) {
        Button buttonClicked = getButtonByXAndY(row, column);

        if (buttonClicked.piece != null) {
            clearPossibilities();

            this.optionList = board.getAllPossibilities(row, column);

            for (Position option : optionList) {
                Button optionButton = getButtonByXAndY(option.getRow(), option.getColumn());
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
            updateBoardMatrix();

            ArrayList<Position> arrayListPosition = board.getAllPossibilities(row, column);
            for (Position positionOption : arrayListPosition) {
                if (Math.abs(positionOption.getRow()-row) == 2 && Math.abs(positionOption.getColumn()-column) == 2) {
                    eatAgain = true;
                }
            }

            System.out.println(eatAgain);


            System.out.println(response);
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
                Piece teste = board.getPiece(row, column);

                if (Objects.equals(teste.team, "white")) {
                    this.numberBlackPieces -= 1;
                } else {
                    this.numberWhitePieces -= 1;
                }
            }
        } else {
            System.out.println("Clicou no nada irmão?");
        }
    }

    // Method to update the board matrix
    public void updateBoardMatrix() {
        board.alocatingBoard();
        createBoard(); // Refresh the board layout
    }

    public void clearPossibilities() {
        for (int i = 0; i < 64; i++) {
            Button button = (Button) this.gridPanel.getComponent(i);
            button.setBorder(new LineBorder(Color.BLACK));
            button.setPossiblePlay(false);
        }
    }

}

