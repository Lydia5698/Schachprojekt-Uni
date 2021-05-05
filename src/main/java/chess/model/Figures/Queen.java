package chess.model.Figures;

import chess.model.CellIndex;

public class Queen extends Minion { //Dame

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
    @Override // checky only rook moves no bishop moves
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();
        // nur diffRow oder! diffColumn darf  nicht null sein,
        if(diffColumn == 0 && diffRow !=0 || diffRow == 0 && diffColumn!=0){
            return true;
        }
        else return Math.abs(diffColumn) == Math.abs(diffRow);


    }
}
