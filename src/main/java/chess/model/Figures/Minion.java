package chess.model.Figures;

import chess.model.CellIndex;

/**
 * Superclass Minions.
 * @author Jasmin Wojtkiewicz
 */
public class Minion {
    protected boolean black;
    protected char minion_type;
    protected String print_minion_type; //ausgabe minion; unterschied groß/klein -> weiß/schwarz

    /**
     * Creates a new instance of Minions. Is overridden in all the subclasses to assign specific letter for the minion.
     * @param black A boolean that determines if the Minion is a white or a black one.
     */
    public Minion(boolean black){ //constructor
       this.black = black;
    }
    public String print_minions(){return this.print_minion_type;}

    public boolean isBlack() {
        return black;
    }

    public char getMinion_type() {
        return minion_type;
    }

    /**
     * Method validMoves checks if the move from one Cell to another is a valid move for a Minion.
     * Is overridden in the subclasses for the specific move pattern of the Minion.
     * @param startIndex
     * @param endIndex
     * @return false
     */

    public boolean validMove(CellIndex startIndex, CellIndex endIndex){
        System.out.println("Abstract Flag");
        return false;
    }


}
