package chess.model;
/**
 * CellIndex the Index of a Cell on the Board. With a row and a column coordinate
 *
 * @author Lydia Günther
 */
public class CellIndex {

    int row;
    int column;
    /**
     * Creates an CellIndex for a coordinate(row, column)
     *
     * @param row    a coordinate for the Cell is the row of the Board(1,2,3,4,5,6,7,8)
     * @param column a coordinate for the Cell is the column of the Board(A,B,D,C,D,E,F,G)
     */

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
