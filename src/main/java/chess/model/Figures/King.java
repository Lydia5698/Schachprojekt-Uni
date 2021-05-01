package chess.model.Figures;

import chess.model.CellIndex;

public class King extends Minions{ //König

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

    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();

        if(diffColumn == 0 && Math.abs(diffRow) ==1 || diffRow == 0 && Math.abs(diffColumn) == 1){
            return true;
        }
        else return Math.abs(diffColumn) == 1 && 1 == Math.abs(diffRow);


    }
}