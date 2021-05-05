package chess.model.Figures;

import chess.model.CellIndex;

/**
 * Queen class represents a Queen on a chessboard.
 * @author Jasmin Wojtkiewicz
 */

public class Queen extends Minions {

    /**
     * Creates a new Queen instance. The Queen can be placed on a Cell.
     * @param black A boolean that determines if the Queen is a white or a black one.
     *              A black Queen can only beat white (not black) pieces and a white Queen can only beat black pieces.
     */

    public Queen(boolean black) {
        super(black);
        this.minion_type = 'Q';
        if(black) {
            print_minion_type = "q";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
    /**
     * Method validMove checks if the move from one Cell to another is a valid move for a Queen.
     * A valid move for a Queen is one to many fields in any direction(horizontally-vertically-diagonal).
     * @param startIndex The coordinates of the start Cell of the move.
     * @param endIndex The coordinates of the end Cell of the move.
     * @return True if the move is valid, false if the move is not valid.
     */
    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();
        // up-down-left-right
        if(diffColumn == 0 && diffRow !=0 || diffRow == 0 && diffColumn!=0){
            return true;
        }
        // diagonal
        else return Math.abs(diffColumn) == Math.abs(diffRow);


    }
}
