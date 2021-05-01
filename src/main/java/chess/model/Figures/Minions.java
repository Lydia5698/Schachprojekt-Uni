package chess.model.Figures;

import chess.model.CellIndex;

public class Minions {
    protected boolean black;
    protected char minion_type;
    protected String print_minion_type; //ausgabe minion; unterschied groß/klein -> weiß/schwarz


    public Minions(boolean black){ //constructor
       this.black = black;
    }
    public String print_minions(){return this.print_minion_type;}

    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        System.out.println("Abstract Flag");
        return false;
    }


}
