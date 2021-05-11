package chess.model.Figures;

import chess.model.CellIndex;

/**
 * King class represents a King on the chessboard.
 * @author Jasmin Wojtkiewicz
 */

public class King extends Minion { //König
    /**
     * Creates a new King instance. The King can be placed on a Cell.
     * @param black A boolean that determines if the King is a white or a black one.
     *              A black King can only beat white (not black) pieces and a white King can only beat black pieces.
     */

    public King(boolean black) {
        super(black);
        this.minion_type = 'K';
        if(black) {
            print_minion_type = "k";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }

    /**
     * Method validMove checks if the move from one Cell to another is a valid move for a King.
     * A valid move for a King is to only one field in any direction.
     * @param startIndex The coordinates of the start Cell of the move.
     * @param endIndex The coordinates of the end Cell of the move.
     * @return True if the move is valid, false if the move is not valid.
     */

    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();
        // up-down-left-right
        if(diffColumn == 0 && Math.abs(diffRow) ==1 || diffRow == 0 && Math.abs(diffColumn) == 1){
            return true;
        }
        //diagonal
        else {return Math.abs(diffColumn) == 1 && 1 == Math.abs(diffRow);}

    }
}