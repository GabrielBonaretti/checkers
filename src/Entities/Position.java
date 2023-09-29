package Entities;

public class Position {
    private int column;
    private int row;

    //E responsavel para mostrar x - linha e com letra e y - numeros.
    public Position() {
        this.column = 1;
        this.row = 1;
    }

    public Position(int x, int y) {
        this.row = x;
        this.column = y;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
