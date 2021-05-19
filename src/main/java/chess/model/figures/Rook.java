package chess.model.figures;

import chess.model.CellIndex;

/**
 * Queen Rook represents a Rook on a chessboard.
 *
 * @author Jasmin Wojtkiewicz
 */

public class Rook extends Minion {

    /**
     * Creates a new Rook instance. The Rook can be placed on a Cell.
     *
     * @param black A boolean that determines if the Rook is a white or a black one.
     *              A black Rook can only beat white (not black) pieces and a white Rook can only beat black pieces.
     */

    public Rook(boolean black) {
        super(black);
        this.minion_type = 'R';
        if (black) {
            print_minion_type = "r";
        } else {
            print_minion_type = String.valueOf(minion_type);
        }
    }

    /**
     * Method validMove checks if the move from one Cell to another is a valid move for a Rook.
     * A valid move for a Rook is one to many fields horizontally or vertically.
     *
     * @param startIndex The coordinates of the start Cell of the move.
     * @param endIndex   The coordinates of the end Cell of the move.
     * @return True if the move is valid, false if the move is not valid.
     */
    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex) {
        int diffRow = endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();
        //up-down-left-right
        return diffColumn == 0 && diffRow != 0 || diffRow == 0 && diffColumn != 0;


    }
}