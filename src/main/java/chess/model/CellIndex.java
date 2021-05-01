package chess.model;

public class CellIndex {

    int row;
    int column;

    public CellIndex(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
