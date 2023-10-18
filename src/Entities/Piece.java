package Entities;

/**
 * The `Piece` class represents a generic game piece in a board game. This class contains
 * information about the piece's name, current position, and team affiliation.
 */
public class Piece {
    public String name;
    public Position position;
    public String team;


    /**
     * Constructor for the `Piece` class.
     *
     * @param name      The name of the game piece.
     * @param position  The initial position of the game piece on the game board.
     * @param team      The team to which the game piece belongs (e.g., "white" or "black").
     */
    public Piece(String name, Position position, String team) {
        this.name = name;
        this.position = position;
        this.team = team;
    }

    /**
     * Sets the position of the game piece to the specified position.
     *
     * @param position The new position of the game piece.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Retrieves the row component of the game piece's current position.
     *
     * @return The row of the game piece's position.
     */
    public int getPositionRow() {
        return position.getRow();
    }

    /**
     * Retrieves the column component of the game piece's current position.
     *
     * @return The column of the game piece's position.
     */
    public int getPositionColumn() {
        return position.getColumn();
    }

    /**
     * Returns a string representation of the game piece, including its name and team.
     *
     * @return A string in the format "name team."
     */
    @Override
    public String toString() {
        return name + " " + team;
    }

}
