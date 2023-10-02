package Entities;

import java.util.ArrayList;
import java.util.Map;

public class Piece {
    public String name;
    public Position position;
    public String team;

    public void setPosition(Position position) {
        this.position = position;
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
