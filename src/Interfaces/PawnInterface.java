package Interfaces;

import Entities.Position;
import Entities.Table;

/**
 * The `PawnInterface` is an interface that extends the `PieceInterface` and defines methods
 * specific to the behavior of a "Pawn" game piece in a board game.
 */
public interface PawnInterface extends PieceInterface {
    /**
     * Tries to make a move with a "Pawn" game piece in a specific direction, considering the game board's state.
     *
     * @param positionTryedParam The position the user is attempting to move the "Pawn" piece to.
     * @param operationRow       The direction of row movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param operationColumn    The direction of column movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param board              The game board on which the "Pawn" piece is placed.
     * @return A response string indicating the result of the move attempt.
     */
    public String tryPlay(Position positionTryedParam, String operationRow, String operationColumn, Table board);

    /**
     * Tries different move possibilities in a specific direction for a "Pawn" game piece,
     * considering the game board's state.
     *
     * @param operationRow    The direction of row movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param operationColumn The direction of column movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param board           The game board on which the "Pawn" piece is placed.
     * @return A `Position` object representing a possible move position.
     */
    public Position tryPossibilities(String operationRow, String operationColumn, Table board);
}
