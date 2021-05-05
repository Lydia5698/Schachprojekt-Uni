package chess.model.Figures;

import chess.model.CellIndex;

public class Rook extends Minion { //Turm

    public Rook(boolean black) {
        super(black);
        this.minion_type = 'R';
        if(black) {
            print_minion_type = "r";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }

    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();
        // nur diffRow oder! diffColumn darf  nicht null sein,
        return diffColumn == 0 && diffRow != 0 || diffRow == 0 && diffColumn != 0;


    }
}