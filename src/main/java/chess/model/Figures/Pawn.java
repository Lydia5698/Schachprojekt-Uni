package chess.model.Figures;

import chess.model.CellIndex;

public class Pawn extends Minions { //Bauer

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
    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        // true false
        int diffRow = startIndex.getRow() - endIndex.getRow() ;
        int diffColumn = startIndex.getColumn() - endIndex.getColumn();
        //ein schritt
        if(diffColumn == 0 && diffRow == 1 && !black || diffColumn == 0 && diffRow == -1 && black ){
            return true;
        }
        return startIndex.getRow() == 8 - 2 && !black && diffColumn == 0 && diffRow == 2 || startIndex.getRow() == 1 && black && diffColumn == 0 && diffRow == -2;
    }
}