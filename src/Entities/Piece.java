package Entities;

public class Piece {
    public String name;
    public Position position;
    public String team;

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getPositionRow() {
        return position.getRow();
    }

    public int getPositionColumn() {
        return position.getColumn();
    }

    public Piece(String name, Position position, String team) {
        this.name = name;
        this.position = position;
        this.team = team;
    }

    @Override
    public String toString() {
        return name + " " + team;
    }
}
