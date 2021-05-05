package chess.model.Figures;

import chess.model.CellIndex;

public class Knight extends Minion { // Hotte Hü

    public Knight(boolean black) {
        super(black);
        this.minion_type = 'N';
        if(black) {
            print_minion_type = "n";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }

    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow =  endIndex.getRow() - startIndex.getRow();
        int diffColumn = endIndex.getColumn() - startIndex.getColumn();

        return Math.abs(diffColumn) == 2 && Math.abs(diffRow) == 1 || Math.abs(diffRow) == 2 && Math.abs(diffColumn) == 1;


    }
}
