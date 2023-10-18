package Interfaces;

import Entities.Position;
import Entities.Table;

import java.util.ArrayList;

/**
 * The `LadyInterface` is an interface that extends the `PieceInterface` and defines methods
 * specific to the behavior of a "Lady" game piece in a board game.
 */
public interface LadyInterface extends PieceInterface {
    /**
     * Tries different move possibilities in a specific direction for a "Lady" game piece,
     * considering the game board's state.
     *
     * @param operationRow    The direction of row movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param operationColumn The direction of column movement (e.g., "NEGATIVE" or "POSITIVE").
     * @param board           The game board on which the "Lady" piece is placed.
     * @return An `ArrayList` of `Position` objects representing possible move positions in a specific direction.
     */
    public ArrayList<Position> tryPossibilities(String operationRow, String operationColumn, Table board);
}
