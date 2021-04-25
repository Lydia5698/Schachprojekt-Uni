package chess.model;

import java.util.ArrayList;

public abstract class Minions {
    protected boolean black;
    private ArrayList<Position> position_valid = new ArrayList<>(); //positionsbestimmung/mögliche züge AN
    protected char minion_type;
    protected String print_minion_type; //ausgabe minion; unterschied groß/klein -> weiß/schwarz
    protected Position current_position; // momentane koords des objektes minion

    public Minions(Position pos,boolean black){ //constructor
        this.current_position=pos;
        this.black=black;
    }
    public String print_minions(){return this.print_minion_type;}
}
