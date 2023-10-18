package Interfaces;

import Entities.Position;
import Entities.Table;

import java.util.ArrayList;

/**
 * The `PieceInterface` is an interface that defines methods common to all game pieces in a board game.
 * It specifies the behavior of a game piece, such as moving, identifying possible moves, and handling eating.
 */
public interface PieceInterface {
    /**
     * Handles a user's attempt to make a move with a game piece and updates the game board accordingly.
     *
     * @param turn              The current turn's team ("white" or "black").
     * @param arrayPositions    An `ArrayList` of possible move positions for the game piece.
     * @param positionTryedParam The position the user is attempting to move the game piece to.
     * @param board             The game board on which the game piece is placed.
     * @return A response string indicating the result of the move attempt.
     */
    public String userPlay(String turn, ArrayList<Position> arrayPositions, Position positionTryedParam, Table board);

    /**
     * Retrieves all possible move positions for a game piece, considering the game board's current state.
     *
     * @param board The game board on which the game piece is placed.
     * @return An `ArrayList` of `Position` objects representing possible move positions.
     */
    public ArrayList<Position> getAllPossibilities(Table board);

    /**
     * Checks if a game piece can make another move to eat an opponent's piece.
     *
     * @param arrayListPosition An `ArrayList` of possible move positions for the game piece.
     * @param row              The row of the piece being considered for eating.
     * @param column           The column of the piece being considered for eating.
     * @return `true` if the game piece can eat again in the current turn; otherwise, `false`.
     */
    public boolean eatAgain(ArrayList<Position> arrayListPosition, int row, int column);
}
