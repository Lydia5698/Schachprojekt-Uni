package chess.model.Figures;

import chess.model.CellIndex;

public class Bishop extends Minion { //Läufer

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

    @Override
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();

        return Math.abs(diffColumn) == Math.abs(diffRow);


    }


}
