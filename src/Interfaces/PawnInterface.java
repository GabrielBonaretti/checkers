package Interfaces;

import Entities.Position;
import Entities.Table;

public interface PawnInterface extends PieceInterface {
    public String tryPlay(Position positionTryedParam, String operationRow, String operationColumn, Table board);
    public Position tryPossibilities(String operationRow, String operationColumn, Table board);
}
