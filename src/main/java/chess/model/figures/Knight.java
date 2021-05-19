package chess.model.figures;

import chess.model.CellIndex;

/**
 * Knight class represents a Knight on a chessboard.
 *
 * @author Jasmin Wojtkiewicz
 */

public class Knight extends Minion {
    /**
     * Creates a new Knight instance. The Knight can be placed on a Cell.
     *
     * @param black A boolean that determines if the Knight is a white or a black one.
     *              A black Knight can only beat white (not black) pieces and a white Knight can only beat black pieces.
     */

    public Knight(boolean black) {
        super(black);
        this.minion_type = 'N';
        if (black) {
            print_minion_type = "n";
        } else {
            print_minion_type = String.valueOf(minion_type);
        }
    }

    /**
     * Method validMove checks if the move from one Cell to another is a valid move for a Knight.
     * A valid move for a Knight is to one field sideways and two field up/down, or two fields sideways and one field up/down.
     *
     * @param startIndex The coordinates of the start Cell of the move.
     * @param endIndex   The coordinates of the end Cell of the move.
     * @return True if the move is valid, false if the move is not valid.
     */

    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex) {

        int diffRow = endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();

        return Math.abs(diffColumn) == 2 && Math.abs(diffRow) == 1 || Math.abs(diffRow) == 2 && Math.abs(diffColumn) == 1;


    }
}
