package Entities;

import java.util.ArrayList;
import java.util.Map;

public class Piece {
    public String name;
    public Position position;
    public String team;


    public Piece(String name, Position position, String team) {
        this.name = name;
        this.position = position;
        this.team = team;
    }

    public void changePosition(
            ArrayList<Map<String, Object>> leftUpDiagonal,
            ArrayList<Map<String, Object>> rightUpDiagonal,
            ArrayList<Map<String, Object>> rightDownDiagonal,
            ArrayList<Map<String, Object>> leftDownDiagonal,
            int newRow,
            int newColumn
    ) {
        for (Map<String, Object> diagonal : leftUpDiagonal) {
            System.out.println(diagonal.get("row"));
            System.out.println(diagonal.get("column"));
            System.out.println(diagonal.get("piece"));
        }

        position.setRow(newRow);
        position.setColumn(newColumn);
    }

    @Override
    public String toString() {
        return name + " " + team;
    }
}
