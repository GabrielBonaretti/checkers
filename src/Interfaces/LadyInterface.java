package Interfaces;

import Entities.Position;
import Entities.Table;

import java.util.ArrayList;

public interface LadyInterface extends PieceInterface {
    public ArrayList<Position> tryPossibilities(String operationRow, String operationColumn, Table board);
}
