package chess.model.figures;

import chess.model.CellIndex;

/**
 * Bishop class represents a Bishop on a chessboard.
 * @author Jasmin Wojtkiewicz
 */

public class Bishop extends Minion { //Läufer
    /**
     * Creates a new Bishop instance. The Bishop can be placed on a Cell.
     * @param black A boolean that determines if the Bishop is a white or a black one.
     *              A black Bishop can only beat white (not black) pieces and a white Bishop can only beat black pieces.
     */

    public Bishop(boolean black) {
        super(black);
        this.minion_type = 'B';
        if(black) {
            print_minion_type = "b";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }

    /**
     * Method validMove checks if the move from one Cell to another is a valid move for a Bishop.
     * A valid move for a Bishop is to only go diagonal.
     * @param startIndex The coordinates of the start Cell of the move.
     * @param endIndex The coordinates of the end Cell of the move.
     * @return True if the move is valid, false if the move is not valid.
     */

    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();

        return Math.abs(diffColumn) == Math.abs(diffRow);

    }


}
