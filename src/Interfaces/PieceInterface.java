package Interfaces;

import Entities.Position;
import Entities.Table;

import java.util.ArrayList;

public interface PieceInterface {
    public String userPlay(String turn, ArrayList<Position> arrayPositions, Position positionTryedParam, Table board);
    public ArrayList<Position> getAllPossibilities(Table board);
    public boolean eatAgain(ArrayList<Position> arrayListPosition, int row, int column);
}
