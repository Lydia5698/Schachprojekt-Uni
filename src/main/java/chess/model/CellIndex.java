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

    public CellIndex(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * makes an Index to a move as an String
     *
     * @param index the start Index
     * @param end   the end Index
     * @return the move as an String
     */
    public String makeIndexIntoString(CellIndex index, CellIndex end) {
        char[] boardLetters = "abcdefgh".toCharArray();
        String idx = "";
        String idx1 = "";
        String idx2 = "";
        String number = Integer.toString(8 - index.getRow());
        Character letter = boardLetters[index.getColumn()];
        idx1 = letter + number;
        String number2 = Integer.toString(8 - end.getRow());
        Character letter2 = boardLetters[end.getColumn()];
        idx2 = letter2 + number2;

        idx = idx1 + "-" + idx2;
        return idx;
    }
}
