package chess.model;

/**
 * CellIndex the Index of a Cell on the Board. With a row and a column coordinate
 *
 * @author Lydia Günther, Jasmin Wojtkiewicz
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

    public String makeIndexIntoString(CellIndex index, CellIndex end){
        char[] boardLetters = "abcdefgh".toCharArray();
        String idx = "";
        String idx1 = "";
        String idx2 = "";
        String number = Integer.toString(index.getRow()+1);
        Character letter = boardLetters[index.getRow()];
        idx1 = String.valueOf(letter) + number;
        String number2 = Integer.toString(end.getRow()+1);
        Character letter2 = boardLetters[end.getRow()];
        idx2 = String.valueOf((letter2)) + number2;

        idx = idx1 + "-" + idx2;
        return idx;
    }
}
