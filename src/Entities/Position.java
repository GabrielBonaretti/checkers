package Entities;

/**
 * The `Position` class represents a two-dimensional position with row and column components.
 * It is used to specify the position of game pieces on the game board.
 */
public class Position {
    private int column;
    private int row;

    /**
     * Default constructor for the `Position` class. Initializes the position to (9, 9).
     */
    public Position() {
        this.row = 9;
        this.column = 9;
    }

    /**
     * Parameterized constructor for the `Position` class. Initializes the position with the specified row and column values.
     *
     * @param x The row component of the position.
     * @param y The column component of the position.
     */
    public Position(int x, int y) {
        this.row = x;
        this.column = y;
    }

    /**
     * Retrieves the column component of the position.
     *
     * @return The column value of the position.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Retrieves the row component of the position.
     *
     * @return The row value of the position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the column component of the position to the specified value.
     *
     * @param column The new column value for the position.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Sets the row component of the position to the specified value.
     *
     * @param row The new row value for the position.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns a string representation of the position in the format "row:column."
     *
     * @return A string representing the position.
     */
    @Override
    public String toString() {
        return row + ":" + column;
    }
}
