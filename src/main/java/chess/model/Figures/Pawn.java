package chess.model.Figures;

import chess.model.CellIndex;

/**
 * Pawn class represents a Pawn on a chessboard.
 * @author Jasmin Wojtkiewicz
 */

public class Pawn extends Minion {

    /**
     * Creates a new Pawn instance. The Pawn can be placed on a Cell.
     * @param black A boolean that determines if the Pawn is a white or a black one.
     *              A black Pawn can only beat white (not black) pieces and a white Pawn can only beat black pieces.
     */
    public Pawn(boolean black) {
        super(black);
        this.minion_type = 'P';
        if(black) {
            print_minion_type = "p";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }

    /**
     * Method validMove checks if the move from one Cell to another is a valid move for a Pawn.
     * A valid move for a Pawn is to only one field up or two fields up if first move of the Pawn.
     * @param startIndex The coordinates of the start Cell of the move.
     * @param endIndex The coordinates of the end Cell of the move.
     * @return True if the move is valid, false if the move is not valid.
     */
    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        int diffRow = startIndex.getRow() - endIndex.getRow() ;
        int diffColumn = startIndex.getColumn() - endIndex.getColumn();
        //one field
        if(diffColumn == 0 && diffRow == 1 && !black || diffColumn == 0 && diffRow == -1 && black ){
            return true;
        }
        //two fields if first move (check start row)
        return startIndex.getRow() == 8 - 2 && !black && diffColumn == 0 && diffRow == 2 || startIndex.getRow() == 1 && black && diffColumn == 0 && diffRow == -2;
        // check if enpassant is true
        // -> enpassantCellIndex1 und enpassantCellIndex2 ok als move && endIndex== empty
    }
}